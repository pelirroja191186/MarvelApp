package com.yhq.marvel.data.domain.model.character

data class CharacterDataModel(
    val count: Int = 0,
    val limit: Int = 0,
    val offset: Int = 0,
    val total: Int = 0,
    val results: List<CharacterItemModel> = emptyList()
) {}
