package com.yhq.marvel.data.model.character

import com.google.common.truth.Truth
import org.junit.*

class CharacterItemResponseTest {
    private val id = 10
    private val name = "name"
    private val description = "description"
    private val url = "url"
    private val extension = "ext"
    private val thumbnail = ThumbnailResponse(extension, url)

    private val characterItemResponseOK = CharacterItemResponse(id, name, description, thumbnail)
    private val characterItemResponseNull = CharacterItemResponse(null, null, null, null)

    @Test
    fun `should character item response ok`() {

        Truth.assertThat(characterItemResponseOK).isNotNull()
        Truth.assertThat(characterItemResponseOK.id).isEqualTo(id)
        Truth.assertThat(characterItemResponseOK.name).isEqualTo(name)
        Truth.assertThat(characterItemResponseOK.description).isEqualTo(description)
        Truth.assertThat(characterItemResponseOK.thumbnail).isNotNull()
    }

    @Test
    fun `should return ok model`() {

        val model = characterItemResponseOK.toCharacterItemModel()

        Truth.assertThat(model).isNotNull()
        Truth.assertThat(model.id).isEqualTo(id)
        Truth.assertThat(model.name).isEqualTo(name)
        Truth.assertThat(model.description).isEqualTo(description)
        Truth.assertThat(model.image).isNotNull()
    }

    @Test
    fun `should return empty model with null response params`() {

        val model = characterItemResponseNull.toCharacterItemModel()

        Truth.assertThat(model).isNotNull()
        Truth.assertThat(model.id).isEqualTo(0)
        Truth.assertThat(model.name).isEmpty()
        Truth.assertThat(model.description).isEmpty()
        Truth.assertThat(model.image).isNotNull()
    }
}
