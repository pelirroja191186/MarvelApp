package com.yhq.marvel.core.base

import com.google.common.truth.Truth
import com.yhq.marvel.core.result.Either
import com.yhq.marvel.core.result.Failure.ServerError
import io.mockk.*
import io.mockk.impl.annotations.*
import org.junit.*
import retrofit2.Call
import retrofit2.Response

class BaseRepositoryTest {


    @RelaxedMockK
    private lateinit var testCall: Call<TestResponse>

    @RelaxedMockK
    private lateinit var testResponse: Response<TestResponse>

    private lateinit var baseRepository: BaseRepository
    private val exampleTest = "test"
    private var testResponseObj = TestResponse(exampleTest)

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        baseRepository = BaseRepository()
    }

    @Test
    fun `should get object from service`() {

        every { testResponse.isSuccessful } returns true
        every { testResponse.body() } returns testResponseObj

        every { testCall.execute() } returns testResponse

        val result = baseRepository.call(
            testCall, { it.toTestModel() },
            TestResponse()
        )

        Truth.assertThat(result).isInstanceOf(Either::class.java)
        Truth.assertThat(result.isRight).isEqualTo(true)
        Truth.assertThat(result.fold({ }, { it })).isInstanceOf(TestModel::class.java)
        Truth.assertThat(result.fold({ }, { it })).isEqualTo(testResponseObj.toTestModel())
    }

    @Test
    fun `should return ServerError when catch exceptions`() {

        every { testCall.execute() } throws IllegalStateException("ops")

        val result = baseRepository.call(
            testCall, { it.toTestModel() },
            TestResponse()
        )

        Truth.assertThat(result).isInstanceOf(Either::class.java)
        Truth.assertThat(result.isLeft).isEqualTo(true)
        Truth.assertThat(result.fold({ failure -> failure }, {})).isInstanceOf(ServerError::class.java)
    }
}

data class TestResponse(val value: String = "") {
    fun toTestModel() = TestModel(value)
}

data class TestModel(val value: String)
