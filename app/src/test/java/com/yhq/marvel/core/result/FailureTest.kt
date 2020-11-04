package com.yhq.marvel.core.result

import com.google.common.truth.Truth
import org.junit.*

class FailureTest {
    val codeNoContent = 204
    val codeUnauthorized = 401
    val codeServerError = 800
    val msgFailure = "test"

    @Test
    fun `handleFailure NoContent ok`() {

        val failure = handleFailure(codeNoContent, msgFailure)

        Truth.assertThat(failure).isNotNull()

        Truth.assertThat(failure).isInstanceOf(Failure.NoContent::class.java)

    }

    @Test
    fun `handleFailure Unauthorized ok`() {

        val failure = handleFailure(codeUnauthorized, msgFailure)

        Truth.assertThat(failure).isNotNull()

        Truth.assertThat(failure).isInstanceOf(Failure.Unauthorized::class.java)
    }

    @Test
    fun `handleFailure ServerError ok`() {

        val failure = handleFailure(codeServerError, msgFailure)

        Truth.assertThat(failure).isNotNull()

        Truth.assertThat(failure).isInstanceOf(Failure.ServerError::class.java)
        Truth.assertThat((failure as Failure.ServerError).message).isNotNull()
        Truth.assertThat((failure).message).isEqualTo(msgFailure)
    }
}
