package com.yhq.marvel.data.di

import com.yhq.marvel.core.utils.ApiParameterHandler
import com.yhq.marvel.core.utils.NetworkHandler
import com.yhq.marvel.data.domain.repository.CharacterRepository
import com.yhq.marvel.data.remote.CharacterRepositoryImp
import com.yhq.marvel.data.service.CharacterService
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class RepositoryModule @Inject constructor() {

    @Singleton
    @Provides
    fun provideCharacterRepository(
        networkHandler: NetworkHandler,
        characterService: CharacterService,
        apiParameterHandler: ApiParameterHandler
    ): CharacterRepository {
        return CharacterRepositoryImp(
            networkHandler,
            characterService,
            apiParameterHandler
        )
    }
}
