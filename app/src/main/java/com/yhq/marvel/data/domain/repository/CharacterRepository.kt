package com.yhq.marvel.data.domain.repository

import com.yhq.marvel.core.result.Either
import com.yhq.marvel.core.result.Failure
import com.yhq.marvel.data.domain.model.character.CharacterDataModel

interface CharacterRepository {
    fun getCharacterList(offset: Int = 0): Either<Failure, CharacterDataModel>
}
