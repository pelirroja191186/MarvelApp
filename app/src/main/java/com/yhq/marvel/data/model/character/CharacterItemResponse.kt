package com.yhq.marvel.data.model.character

import com.squareup.moshi.JsonClass
import com.yhq.marvel.core.extension.orDefault
import com.yhq.marvel.data.domain.model.character.CharacterItemModel
import com.yhq.marvel.data.domain.model.character.ThumbnailModel

@JsonClass(generateAdapter = true)
data class CharacterItemResponse(
    val id: Int?,
    val name: String?,
    val description: String?,
    val thumbnail: ThumbnailResponse?
) {

    fun toCharacterItemModel() =
        CharacterItemModel(
            id.orDefault(),
            name.orDefault(),
            description.orDefault(),
            thumbnail?.toThumbnailModel() ?: ThumbnailModel()
        )
}
