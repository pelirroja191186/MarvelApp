package com.yhq.marvel.data.model.character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.yhq.marvel.data.domain.model.character.CharacterDataModel
import com.yhq.marvel.data.domain.model.character.CharacterModel

@JsonClass(generateAdapter = true)
data class CharacterResponse(
    @Json(name = "data")
    var characterDataResponse: CharacterDataResponse? = CharacterDataResponse()
) {
    fun toCharacterModel() =
        CharacterModel(characterDataResponse?.let { it.toCharacterDataModel() } ?: CharacterDataModel())
}
