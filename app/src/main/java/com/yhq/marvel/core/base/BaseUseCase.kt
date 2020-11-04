package com.yhq.marvel.core.base

import com.yhq.marvel.core.result.Either
import com.yhq.marvel.core.result.Failure
import com.yhq.marvel.core.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class BaseUseCase<out Model, in Params>(private val coroutineContextProvider: CoroutineContextProvider) where Model : Any {

    abstract suspend fun run(params: Params): Either<Failure, Model>

    operator fun invoke(params: Params, onResult: (Either<Failure, Model>) -> Unit = {}) {
        val ioScope = CoroutineScope(coroutineContextProvider.io).async { run(params) }
        CoroutineScope(coroutineContextProvider.default).launch { onResult(ioScope.await()) }
    }

    class NoneParameters
}
