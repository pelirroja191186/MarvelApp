package com.yhq.marvel.data.service

import com.yhq.marvel.data.constant.Queries.API_KEY
import com.yhq.marvel.data.constant.Queries.HASH
import com.yhq.marvel.data.constant.Queries.LIMIT
import com.yhq.marvel.data.constant.Queries.LIMIT_VALUE
import com.yhq.marvel.data.constant.Queries.OFFSET
import com.yhq.marvel.data.constant.Queries.TIMESTAMP
import com.yhq.marvel.data.model.character.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CharacterService {

    @GET("/v1/public/characters")
    fun getListOfCharacters(
        @Query(TIMESTAMP) timestamp: String,
        @Query(API_KEY) apiKey: String,
        @Query(HASH) hash: String,
        @Query(OFFSET) offset: Int,
        @Query(LIMIT) limit: Int = LIMIT_VALUE
    ): Call<CharacterResponse>
}

