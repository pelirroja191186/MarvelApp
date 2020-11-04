package com.yhq.marvel.data.usecase

import com.google.common.truth.Truth
import com.yhq.marvel.core.base.BaseUseCase.NoneParameters
import com.yhq.marvel.core.result.Either
import com.yhq.marvel.core.result.Either.Right
import com.yhq.marvel.core.utils.TestCoroutineContextProvider
import com.yhq.marvel.data.domain.repository.CharacterRepository
import com.yhq.marvel.data.domain.usecase.CharacterUseCase
import com.yhq.marvel.data.domain.usecase.CharacterUseCase.CharacterParam
import com.yhq.marvel.data.model.character.CharacterDataResponse
import com.yhq.marvel.data.model.character.CharacterResponse
import io.mockk.*
import io.mockk.impl.annotations.*
import kotlinx.coroutines.runBlocking
import org.junit.*

class CharacterUseCaseTest {
    private lateinit var characterUseCase: CharacterUseCase

    @RelaxedMockK
    private lateinit var characterRepository: CharacterRepository

    private val count = 10
    private val limit = 20
    private val offset = 2
    private val total = 200
    private var characterDataResponse: CharacterDataResponse = CharacterDataResponse(count, limit, offset, total)
    private val characterResponseTest = CharacterResponse(characterDataResponse)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        characterUseCase = CharacterUseCase(characterRepository, TestCoroutineContextProvider())

        every { characterRepository.getCharacterList(offset) } returns Right(characterResponseTest.toCharacterModel().characterDataModel)
    }

    @Test
    fun `should get data from repository`() {
        val result = runBlocking { characterUseCase.run(CharacterParam(offset)) }

        Truth.assertThat(result).isInstanceOf(Either::class.java)
        Truth.assertThat(result.isRight).isEqualTo(true)
        Truth.assertThat(result).isEqualTo(Right(characterResponseTest.toCharacterModel().characterDataModel))
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
