package com.yhq.marvel.core.result

import com.yhq.marvel.R
import com.yhq.marvel.core.result.Failure.NoConnection
import com.yhq.marvel.core.result.Failure.NoContent
import com.yhq.marvel.core.result.Failure.Unauthorized

open class ErrorMessage(
    val titleResourceID: Int,
    val messageResourceID: Int
)

object ErrorNoDefined : ErrorMessage(
    R.string.error_no_defined_title,
    R.string.error_no_defined_message
)

object ErrorNoAuthorized : ErrorMessage(
    R.string.error_no_authorized_title,
    R.string.error_no_authorized_message
)

object ErrorNoConnection : ErrorMessage(
    R.string.error_connection_title,
    R.string.error_connection_message
)

object ErrorNoContent : ErrorMessage(
    R.string.error_no_content_title,
    R.string.error_no_content_message
)

fun getErrorMessage(failure: Failure): ErrorMessage = when (failure) {
    Unauthorized -> ErrorNoAuthorized
    NoConnection -> ErrorNoConnection
    NoContent -> ErrorNoContent
    else -> ErrorNoDefined
}
