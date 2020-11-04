package com.yhq.marvel.data.service

import com.yhq.marvel.core.base.BaseService
import com.yhq.marvel.data.constant.Queries.LIMIT_VALUE
import com.yhq.marvel.data.model.character.CharacterResponse
import retrofit2.Call

@Suppress("UNUSED_PARAMETER")
class CharacterService : BaseService() {

    fun getListOfCharacters(
        timestamp: String = "",
        apiKey: String = "",
        hash: String = "",
        offset: Int = 0,
        limit: Int = LIMIT_VALUE
    ): Call<CharacterResponse> {
        val fileName = "character_list.json"
        return createMockCall(fileName, CharacterResponse::class.java)
    }

}
