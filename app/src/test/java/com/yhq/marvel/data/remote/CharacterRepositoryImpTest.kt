package com.yhq.marvel.data.remote

import com.google.common.truth.Truth
import com.yhq.marvel.core.result.Either
import com.yhq.marvel.core.result.Either.Right
import com.yhq.marvel.core.result.Failure.NoConnection
import com.yhq.marvel.core.result.Failure.ServerError
import com.yhq.marvel.core.utils.ApiParameterHandler
import com.yhq.marvel.core.utils.NetworkHandler
import com.yhq.marvel.data.domain.repository.CharacterRepository
import com.yhq.marvel.data.model.character.CharacterDataResponse
import com.yhq.marvel.data.model.character.CharacterResponse
import com.yhq.marvel.data.service.CharacterService
import io.mockk.*
import io.mockk.impl.annotations.*
import org.junit.*
import retrofit2.Call
import retrofit2.Response

class CharacterRepositoryImpTest {

    private lateinit var characterRepository: CharacterRepository

    @RelaxedMockK
    private lateinit var networkHandler: NetworkHandler

    @RelaxedMockK
    private lateinit var characterService: CharacterService

    @RelaxedMockK
    private lateinit var characterCall: Call<CharacterResponse>

    @RelaxedMockK
    private lateinit var characterResponse: Response<CharacterResponse>

    private val count = 10
    private val limit = 20
    private val offset = 2
    private val total = 200
    private var characterDataResponse: CharacterDataResponse = CharacterDataResponse(count, limit, offset, total)
    private val characterResponseTest = CharacterResponse(characterDataResponse)

    private val apiParameterHandler =  ApiParameterHandler()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        characterRepository = CharacterRepositoryImp(networkHandler, characterService,apiParameterHandler)
    }

    @Test
    fun `should return empty object by default`() {
        every { networkHandler.isConnected } returns true

        every { characterResponse.isSuccessful } returns true
        every { characterResponse.body() } returns null

        every { characterCall.execute() } returns characterResponse

        every { characterService.getListOfCharacters(apiParameterHandler.ts.toString(), apiParameterHandler.publicKey, apiParameterHandler.hash, offset) } returns characterCall

        val characterListResult = characterRepository.getCharacterList(offset)
        Truth.assertThat(characterListResult)
            .isEqualTo(Right(CharacterResponse().toCharacterModel().characterDataModel))
    }

    @Test
    fun `should get object from service`() {
        every { networkHandler.isConnected } returns true

        every { characterResponse.isSuccessful } returns true
        every { characterResponse.body() } returns characterResponseTest

        every { characterCall.execute() } returns characterResponse
        every { characterService.getListOfCharacters(apiParameterHandler.ts.toString(), apiParameterHandler.publicKey, apiParameterHandler.hash, offset) } returns characterCall

        val characterListResult = characterRepository.getCharacterList(offset)
        Truth.assertThat(characterListResult)
            .isEqualTo(Right(characterResponseTest.toCharacterModel().characterDataModel))
    }

    @Test
    fun `should return NoConnection when no connection`() {
        every { networkHandler.isConnected } returns false

        val characterListResult = characterRepository.getCharacterList(offset)

        Truth.assertThat(characterListResult).isInstanceOf(Either::class.java)
        Truth.assertThat(characterListResult.isLeft).isEqualTo(true)
        Truth.assertThat(characterListResult.fold({ failure -> failure }, {})).isInstanceOf(NoConnection::class.java)
    }

    @Test
    fun `should return ServerError if no successful response`() {
        every { networkHandler.isConnected } returns true

        val characterListResult = characterRepository.getCharacterList(offset)

        Truth.assertThat(characterListResult).isInstanceOf(Either::class.java)
        Truth.assertThat(characterListResult.isLeft).isEqualTo(true)
        Truth.assertThat(characterListResult.fold({ failure -> failure }, {})).isInstanceOf(ServerError::class.java)
    }

    @Test
    fun `should return ServerError when catch exceptions`() {
        every { networkHandler.isConnected } returns true

        every { characterCall.execute() } throws IllegalStateException("ops")

        val characterListResult = characterRepository.getCharacterList(offset)

        Truth.assertThat(characterListResult).isInstanceOf(Either::class.java)
        Truth.assertThat(characterListResult.isLeft).isEqualTo(true)
        Truth.assertThat(characterListResult.fold({ failure -> failure }, {})).isInstanceOf(ServerError::class.java)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}
