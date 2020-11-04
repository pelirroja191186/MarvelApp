package com.yhq.marvel.data.domain.usecase

import com.yhq.marvel.core.base.BaseUseCase
import com.yhq.marvel.core.result.Either
import com.yhq.marvel.core.result.Failure
import com.yhq.marvel.core.utils.CoroutineContextProvider
import com.yhq.marvel.data.domain.model.character.CharacterDataModel
import com.yhq.marvel.data.domain.repository.CharacterRepository
import com.yhq.marvel.data.domain.usecase.CharacterUseCase.CharacterParam
import javax.inject.Inject

class CharacterUseCase
@Inject constructor(
    private val characterRepository: CharacterRepository,
    coroutineContextProvider: CoroutineContextProvider
) : BaseUseCase<CharacterDataModel, CharacterParam>(coroutineContextProvider) {

    override suspend fun run(params: CharacterParam): Either<Failure, CharacterDataModel> =
        characterRepository.getCharacterList(params.offset)

    data class CharacterParam(val offset: Int = 0)
}


