package com.yhq.marvel.core.base

import com.yhq.marvel.core.result.Either
import com.yhq.marvel.core.result.Failure
import com.yhq.marvel.core.result.handleFailure
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

open class BaseRepository @Inject constructor() {

    fun <T, R> call(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(handleErrorResponse(response))
            }
        } catch (exception: Throwable) {
            Either.Left(handleThrowable(exception))
        }
    }

    private fun <T> handleErrorResponse(response: Response<T>): Failure {
        return handleFailure(response.code(), response.message())
    }

    private fun handleThrowable(exception: Throwable): Failure {
        return handleFailure(exception.cause.hashCode(), exception.message)
    }
}
