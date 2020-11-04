package com.yhq.marvel.features.character.ui.list.model

import com.yhq.marvel.data.domain.model.character.CharacterItemModel
import com.yhq.marvel.data.domain.model.character.generateThumbnailURL

data class CharacterItemUIModel(
    val id: Int,
    val name: String,
    val description: String,
    val imageURL: String
) : ItemCharacterList

fun CharacterItemModel.toCharacterUIModel(): CharacterItemUIModel =
    CharacterItemUIModel(
        this.id,
        this.name,
        this.description,
        image.generateThumbnailURL()
    )

