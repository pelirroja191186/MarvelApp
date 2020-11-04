package com.yhq.marvel.core.result

import com.google.common.truth.Truth
import org.junit.*

class ErrorTest {
    val failureErrorNoAuthorized = Failure.Unauthorized
    val failureErrorNoConnection = Failure.NoConnection
    val failureErrorNoContent = Failure.NoContent
    val failureErrorNoDefined = Failure.ServerError()

    @Test
    fun `ErrorMessage Unauthorized ok`() {

        val errorMessage = getErrorMessage(failureErrorNoAuthorized)

        Truth.assertThat(errorMessage).isNotNull()

        Truth.assertThat(errorMessage.titleResourceID).isEqualTo(ErrorNoAuthorized.titleResourceID)
        Truth.assertThat(errorMessage.messageResourceID).isEqualTo(ErrorNoAuthorized.messageResourceID)
    }

    @Test
    fun `ErrorMessage NoConnection ok`() {

        val errorMessage = getErrorMessage(failureErrorNoConnection)

        Truth.assertThat(errorMessage).isNotNull()

        Truth.assertThat(errorMessage.titleResourceID).isEqualTo(ErrorNoConnection.titleResourceID)
        Truth.assertThat(errorMessage.messageResourceID).isEqualTo(ErrorNoConnection.messageResourceID)
    }

    @Test
    fun `ErrorMessage NoContent ok`() {

        val errorMessage = getErrorMessage(failureErrorNoContent)

        Truth.assertThat(errorMessage).isNotNull()

        Truth.assertThat(errorMessage.titleResourceID).isEqualTo(ErrorNoContent.titleResourceID)
        Truth.assertThat(errorMessage.messageResourceID).isEqualTo(ErrorNoContent.messageResourceID)
    }

    @Test
    fun `ErrorMessage NoDefined ok`() {

        val errorMessage = getErrorMessage(failureErrorNoDefined)

        Truth.assertThat(errorMessage).isNotNull()

        Truth.assertThat(errorMessage.titleResourceID).isEqualTo(ErrorNoDefined.titleResourceID)
        Truth.assertThat(errorMessage.messageResourceID).isEqualTo(ErrorNoDefined.messageResourceID)
    }
}
