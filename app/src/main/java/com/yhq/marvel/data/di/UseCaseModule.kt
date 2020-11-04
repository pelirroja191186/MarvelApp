package com.yhq.marvel.data.di

import com.yhq.marvel.core.utils.CoroutineContextProvider
import com.yhq.marvel.data.domain.repository.CharacterRepository
import com.yhq.marvel.data.domain.usecase.CharacterUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class UseCaseModule @Inject constructor() {

    @Singleton
    @Provides
    fun provideCharacterUseCase(
        characterRepository: CharacterRepository,
        coroutineContextProvider: CoroutineContextProvider
    ): CharacterUseCase {
        return CharacterUseCase(
            characterRepository,
            coroutineContextProvider
        )
    }
}
