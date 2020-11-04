package com.yhq.marvel.data.model.character

import com.squareup.moshi.JsonClass
import com.yhq.marvel.core.extension.orDefault
import com.yhq.marvel.data.domain.model.character.ThumbnailModel

@JsonClass(generateAdapter = true)
data class ThumbnailResponse(
    val extension: String? = "",
    val path: String? = ""
) {

    fun toThumbnailModel() =
        ThumbnailModel(extension.orDefault(), path.orDefault())
}
