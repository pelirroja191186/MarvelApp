package com.yhq.marvel.data.model.character

import com.google.common.truth.Truth
import org.junit.*

class CharacterResponseTest {
    private val count = 10
    private val limit = 20
    private val offset = 2
    private val total = 200
    private var characterDataResponse: CharacterDataResponse = CharacterDataResponse(count, limit, offset, total)

    private val characterResponseOK = CharacterResponse(characterDataResponse)
    private val characterResponseNull = CharacterResponse(null)

    @Test
    fun `should character response ok`() {

        Truth.assertThat(characterResponseOK).isNotNull()
        Truth.assertThat(characterResponseOK.characterDataResponse).isNotNull()
    }

    @Test
    fun `should return ok model`() {

        val model = characterResponseOK.toCharacterModel()

        Truth.assertThat(model).isNotNull()
        Truth.assertThat(model.characterDataModel).isNotNull()
    }

    @Test
    fun `should return empty model with null response params`() {

        val model = characterResponseNull.toCharacterModel()

        Truth.assertThat(model).isNotNull()
        Truth.assertThat(model.characterDataModel).isNotNull()
    }
}
