package com.yhq.marvel.data.domain.model.character

data class CharacterItemModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val image: ThumbnailModel = ThumbnailModel()
)
