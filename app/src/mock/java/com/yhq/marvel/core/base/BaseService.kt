package com.yhq.marvel.core.base

import com.squareup.moshi.Moshi
import com.yhq.marvel.core.utils.Constant.MOCK_FILES_PATH
import com.yhq.marvel.core.utils.MockCall
import java.io.InputStream

open class BaseService() {

    fun <T> createMockCall(
        file: String,
        className: Class<T>
    ): MockCall<T> {
        val inputStream = this.javaClass.classLoader?.getResourceAsStream(MOCK_FILES_PATH + file)
        val response: T? = createResponse(inputStream, className)
        return MockCall(response)
    }

    private fun <T> createResponse(inputStream: InputStream?, className: Class<T>): T? {
        inputStream?.apply {
            val inputAsString = this.bufferedReader().use { it.readText() }
            val jsonAdapter = createFactoryConverter().adapter(className)
            return jsonAdapter.fromJson(inputAsString)
        }
        return null
    }

    open fun createFactoryConverter(): Moshi {
        return Moshi.Builder()
            .build()
    }
}

