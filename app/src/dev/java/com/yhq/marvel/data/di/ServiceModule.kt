package com.yhq.marvel.data.di

import com.yhq.marvel.data.service.CharacterService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Module
class ServiceModule @Inject constructor() {

    @Singleton
    @Provides
    fun provideCharacterService(retrofit: Retrofit) =
        retrofit.create(CharacterService::class.java)
}

