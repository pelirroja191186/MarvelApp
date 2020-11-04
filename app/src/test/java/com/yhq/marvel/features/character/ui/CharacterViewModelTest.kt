package com.yhq.marvel.features.character.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.yhq.marvel.core.result.Either.Right
import com.yhq.marvel.core.result.Failure.NoContent
import com.yhq.marvel.data.domain.usecase.CharacterUseCase
import com.yhq.marvel.data.domain.usecase.CharacterUseCase.CharacterParam
import com.yhq.marvel.data.model.character.CharacterDataResponse
import com.yhq.marvel.data.model.character.CharacterItemResponse
import com.yhq.marvel.data.model.character.CharacterResponse
import com.yhq.marvel.data.model.character.ThumbnailResponse
import com.yhq.marvel.features.character.ui.list.model.CharacterItemUIModel
import com.yhq.marvel.features.character.ui.list.model.LoadingUIModel
import io.mockk.*
import io.mockk.impl.annotations.*
import kotlinx.coroutines.runBlocking
import org.junit.*

class CharacterViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var characterViewModel: CharacterViewModel

    @RelaxedMockK
    private lateinit var characterUseCase: CharacterUseCase

    private val count = 10
    private val limit = 20
    private val offset = 2
    private val total = 200

    private val id1 = 10
    private val name1 = "name1"
    private val description1 = "description1"
    private val characterItem1 = CharacterItemResponse(id1, name1, description1, ThumbnailResponse())

    private val id2 = 10
    private val name2 = "name2"
    private val description2 = "description2"
    private val characterItem2 = CharacterItemResponse(id2, name2, description2, ThumbnailResponse())

    private lateinit var characterDataResponse: CharacterDataResponse
    private lateinit var characterResponseTest: CharacterResponse

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        characterViewModel = CharacterViewModel(characterUseCase)

        val listItems = mutableListOf(characterItem1, characterItem2)
        characterDataResponse = CharacterDataResponse(count, limit, offset, total, listItems)
        characterResponseTest = CharacterResponse(characterDataResponse)
    }

    @Test
    fun `load data should update live data`() {

        every { runBlocking { characterUseCase.run(any()) } } returns Right(characterResponseTest.toCharacterModel().characterDataModel)

        checkLiveData()

        runBlocking { characterViewModel.loadData() }
    }

    @Test
    fun `load data with pull to refresh = true should clear live data`() {

        every { runBlocking { characterUseCase.run(CharacterParam(offset)) } } returns Right(characterResponseTest.toCharacterModel().characterDataModel)

        characterViewModel.characterList.observeForever {
            Truth.assertThat(it).isEmpty()
        }

        runBlocking { characterViewModel.loadData(true) }
    }

    @Test
    fun `load data with loading should update live data`() {

        every { runBlocking { characterUseCase.run(CharacterParam(offset)) } } returns Right(characterResponseTest.toCharacterModel().characterDataModel)

        runBlocking { characterViewModel.loadData() }

        characterViewModel.characterList.observeForever {
            Truth.assertThat(it).isNotEmpty()
            Truth.assertThat(it).hasSize(3)
            Truth.assertThat(it[2]).isInstanceOf(LoadingUIModel::class.java)
        }

        runBlocking { characterViewModel.loadData() }
    }

    @Test
    fun `handle result should update live data`() {

        executeHandleCharacterListResult()

        checkLiveData()
    }

    @Test
    fun `handle result should return no content`() {

        val characterEmptyModel = CharacterResponse().toCharacterModel().characterDataModel
        characterViewModel.handleCharacterDataResult(characterEmptyModel)

        characterViewModel.failure.observeForever {
            Truth.assertThat(it).isNotNull()
            Truth.assertThat(it).isInstanceOf(NoContent::class.java)
        }
    }

    private fun executeHandleCharacterListResult() {
        characterViewModel.handleCharacterDataResult(characterResponseTest.toCharacterModel().characterDataModel)
    }

    private fun checkLiveData() {

        characterViewModel.characterList.observeForever {
            Truth.assertThat(it).isNotEmpty()
            Truth.assertThat(it).hasSize(2)
            Truth.assertThat(it[0]).isInstanceOf(CharacterItemUIModel::class.java)
            Truth.assertThat((it[0] as CharacterItemUIModel).id).isEqualTo(id1)
            Truth.assertThat((it[0] as CharacterItemUIModel).name).isEqualTo(name1)
            Truth.assertThat((it[0] as CharacterItemUIModel).description).isEqualTo(description1)

            Truth.assertThat(it[1]).isInstanceOf(CharacterItemUIModel::class.java)
            Truth.assertThat((it[1] as CharacterItemUIModel).id).isEqualTo(id2)
            Truth.assertThat((it[1] as CharacterItemUIModel).name).isEqualTo(name2)
            Truth.assertThat((it[1] as CharacterItemUIModel).description).isEqualTo(description2)
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
