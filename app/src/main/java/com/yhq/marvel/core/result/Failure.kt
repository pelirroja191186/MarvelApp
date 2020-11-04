package com.yhq.marvel.core.result

sealed class Failure {
    object NoContent : Failure()
    object Unauthorized : Failure()
    object NoConnection : Failure()

    data class ServerError(val code: Int? = null, val message: String? = null) : Failure()
}

fun handleFailure(code: Int? = null, message: String? = null): Failure =
    when (code) {
        204 -> Failure.NoContent
        401 -> Failure.Unauthorized

        else -> Failure.ServerError(code, message)
    }

