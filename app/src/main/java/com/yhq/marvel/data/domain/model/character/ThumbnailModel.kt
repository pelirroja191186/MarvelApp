package com.yhq.marvel.data.domain.model.character

data class ThumbnailModel(val extension: String = "", val path: String = "")

fun ThumbnailModel.generateThumbnailURL() =
    if (extension.isNotEmpty() && path.isNotEmpty()) "$path.$extension" else ""
