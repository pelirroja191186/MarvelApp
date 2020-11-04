package com.yhq.marvel.core.utils

import android.content.Context
import com.yhq.marvel.BuildConfig
import com.yhq.marvel.core.extension.isConnectedToNetwork
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler
@Inject constructor(private val context: Context) {

    val isConnected get() = BuildConfig.FLAVOR == "mock" || context.isConnectedToNetwork
}
