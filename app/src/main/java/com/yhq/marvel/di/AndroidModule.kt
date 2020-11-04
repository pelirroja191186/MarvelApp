package com.yhq.marvel.di

import android.app.Application
import android.content.Context
import com.yhq.marvel.MarvelApp
import com.yhq.marvel.core.utils.CoroutineContextProvider
import com.yhq.marvel.core.utils.CoroutineContextProviderImp
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class AndroidModule @Inject constructor() {

    @Provides
    internal fun provideApplication(app: MarvelApp): Application {
        return app
    }

    @Provides
    internal fun provideContext(app: MarvelApp): Context {
        return app
    }

    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider {
        return CoroutineContextProviderImp()
    }
}
