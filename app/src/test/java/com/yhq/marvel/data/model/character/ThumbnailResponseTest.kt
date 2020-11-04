package com.yhq.marvel.data.model.character

import com.google.common.truth.Truth
import org.junit.*

class ThumbnailResponseTest {

    private val extension = "extension"
    private val path = "path"

    private val thumbnailResponseOK = ThumbnailResponse(extension, path)
    private val thumbnailResponseNull = ThumbnailResponse(null, null)

    @Test
    fun `should thumbnail response ok`() {

        Truth.assertThat(thumbnailResponseOK).isNotNull()
        Truth.assertThat(thumbnailResponseOK.extension).isEqualTo(extension)
        Truth.assertThat(thumbnailResponseOK.path).isEqualTo(path)
    }

    @Test
    fun `should return ok model`() {

        val model = thumbnailResponseOK.toThumbnailModel()

        Truth.assertThat(model).isNotNull()
        Truth.assertThat(model.extension).isEqualTo(extension)
        Truth.assertThat(model.path).isEqualTo(path)
    }

    @Test
    fun `should return empty model with null response params`() {

        val model = thumbnailResponseNull.toThumbnailModel()

        Truth.assertThat(model).isNotNull()
        Truth.assertThat(model.extension).isEmpty()
        Truth.assertThat(model.path).isEmpty()
    }
}
