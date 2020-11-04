package com.yhq.marvel.data.di

import com.squareup.moshi.Moshi
import com.yhq.marvel.BuildConfig
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module(
    includes = [
        ServiceModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
class DataModule @Inject constructor() {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_HOST)
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder()))
            .build()
    }
}

private fun moshiBuilder(): Moshi {
    return Moshi.Builder()
        .build()
}
