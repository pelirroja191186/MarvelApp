package com.yhq.marvel.core.utils

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    val main: CoroutineContext
    val io: CoroutineContext
    val default: CoroutineContext
}

class CoroutineContextProviderImp @Inject constructor() : CoroutineContextProvider {
    override val main: CoroutineContext by lazy { Dispatchers.Main }
    override val io: CoroutineContext by lazy { Dispatchers.IO }
    override val default: CoroutineContext by lazy { Dispatchers.Default }
}

class TestCoroutineContextProvider : CoroutineContextProvider {
    override val io: CoroutineContext = Dispatchers.Unconfined
    override val main: CoroutineContext = Dispatchers.Unconfined
    override val default: CoroutineContext = Dispatchers.Unconfined
}
