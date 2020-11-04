package com.yhq.marvel.data.model.character

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.yhq.marvel.core.extension.orDefault
import com.yhq.marvel.data.domain.model.character.CharacterDataModel

@JsonClass(generateAdapter = true)
@Json(name = "data")
data class CharacterDataResponse(
    val count: Int? = 0,
    val limit: Int? = 0,
    val offset: Int? = 0,
    val total: Int? = 0,
    val results: List<CharacterItemResponse>? = emptyList()
) {

    fun toCharacterDataModel() =
        CharacterDataModel(
            count.orDefault(),
            limit.orDefault(),
            offset.orDefault(),
            total.orDefault(),
            results?.map { it.toCharacterItemModel() } ?: emptyList()
        )
}
