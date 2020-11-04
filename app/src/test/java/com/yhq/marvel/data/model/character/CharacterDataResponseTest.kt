package com.yhq.marvel.data.model.character

import com.google.common.truth.Truth
import org.junit.*

class CharacterDataResponseTest {
    private val count = 10
    private val limit = 20
    private val offset = 2
    private val total = 200
    private var results: List<CharacterItemResponse>? = emptyList()


   private val characterDataResponseOK = CharacterDataResponse(count,limit,offset,total,results)
   private val characterDatarResponseNull = CharacterDataResponse(null, null,null,null,null)

    @Test
    fun `should character data response ok`() {

        Truth.assertThat(characterDataResponseOK).isNotNull()
        Truth.assertThat(characterDataResponseOK.count).isEqualTo(count)
        Truth.assertThat(characterDataResponseOK.limit).isEqualTo(limit)
        Truth.assertThat(characterDataResponseOK.offset).isEqualTo(offset)
        Truth.assertThat(characterDataResponseOK.total).isEqualTo(total)
        Truth.assertThat(characterDataResponseOK.results).isEmpty()
    }

    @Test
    fun `should return ok model`() {

        val model = characterDataResponseOK.toCharacterDataModel()

        Truth.assertThat(model).isNotNull()
        Truth.assertThat(model.count).isEqualTo(count)
        Truth.assertThat(model.limit).isEqualTo(limit)
        Truth.assertThat(model.offset).isEqualTo(offset)
        Truth.assertThat(model.total).isEqualTo(total)
        Truth.assertThat(model.results).isEmpty()
    }

    @Test
    fun `should return empty model with null response params`() {

        val model = characterDatarResponseNull.toCharacterDataModel()

        Truth.assertThat(model).isNotNull()
        Truth.assertThat(model.count).isEqualTo(0)
        Truth.assertThat(model.limit).isEqualTo(0)
        Truth.assertThat(model.offset).isEqualTo(0)
        Truth.assertThat(model.total).isEqualTo(0)
        Truth.assertThat(model.results).isEmpty()
    }
}
