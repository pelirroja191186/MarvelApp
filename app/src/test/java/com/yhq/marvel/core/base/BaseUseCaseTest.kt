package com.yhq.marvel.core.base

import com.google.common.truth.Truth
import com.yhq.marvel.core.result.Either
import com.yhq.marvel.core.result.Either.Right
import com.yhq.marvel.core.result.Failure
import com.yhq.marvel.core.utils.TestCoroutineContextProvider
import kotlinx.coroutines.runBlocking
import org.junit.*

class BaseUseCaseTest {

    private val MODEL_TEST_VALUE = "modelTest"
    private val PARAM_TEST_VALUE = "paramTest"

    private val testUseCase = TestUseCase()

    val paramsTest = ParamTest(PARAM_TEST_VALUE)

    @Test
    fun `running use case should return ok Either`() {
        val result = runBlocking { testUseCase.run(paramsTest) }
        Truth.assertThat(result).isEqualTo(Right(ModelTest(MODEL_TEST_VALUE)))
    }

    @Test
    fun `running use case should return ok data`() {
        var result: Either<Failure, ModelTest>? = null

        val onResult = { myResult: Either<Failure, ModelTest> -> result = myResult }

        runBlocking { testUseCase(paramsTest, onResult) }

        Truth.assertThat(result).isEqualTo(Right(ModelTest(MODEL_TEST_VALUE)))
    }

    data class ModelTest(val value: String)
    data class ParamTest(val value: String)

    private inner class TestUseCase : BaseUseCase<ModelTest, ParamTest>(TestCoroutineContextProvider()) {
        override suspend fun run(params: ParamTest): Either<Failure, ModelTest> = Right(ModelTest(MODEL_TEST_VALUE))
    }
}


