package com.yhq.marvel.data.remote

import com.yhq.marvel.core.base.BaseRepository
import com.yhq.marvel.core.result.Either
import com.yhq.marvel.core.result.Either.Left
import com.yhq.marvel.core.result.Failure
import com.yhq.marvel.core.utils.ApiParameterHandler
import com.yhq.marvel.core.utils.NetworkHandler
import com.yhq.marvel.data.domain.model.character.CharacterDataModel
import com.yhq.marvel.data.domain.repository.CharacterRepository
import com.yhq.marvel.data.model.character.CharacterResponse
import com.yhq.marvel.data.service.CharacterService
import javax.inject.Inject

class CharacterRepositoryImp @Inject constructor(
    private val networkHandler: NetworkHandler,
    private val characterService: CharacterService,
    private val apiParameterHandler: ApiParameterHandler
) : CharacterRepository, BaseRepository() {

    override fun getCharacterList(offset: Int): Either<Failure, CharacterDataModel> {
        return when (networkHandler.isConnected) {
            true -> {

                // val timestamp = Date().time
                // val hash = md5(timestamp.toString() + BuildConfig.MARVEL_PRIVATE_KEY + BuildConfig.MARVEL_PUBLIC_KEY)

                call(
                    characterService.getListOfCharacters(
                        timestamp = apiParameterHandler.ts.toString(),
                        apiKey = apiParameterHandler.publicKey,
                        hash = apiParameterHandler.hash,
                        offset = offset
                    ),
                    { it.characterDataResponse?.toCharacterDataModel() ?: CharacterDataModel() },
                    CharacterResponse()
                )
            }
            false -> Left(Failure.NoConnection)
        }
    }
}
