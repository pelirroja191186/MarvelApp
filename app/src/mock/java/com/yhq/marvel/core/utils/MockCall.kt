package com.yhq.marvel.core.utils

import com.yhq.marvel.core.utils.Constant.SUCCESS_CODE
import okhttp3.Headers
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MockCall<T> constructor(val response: T?, private val successCode: Int = SUCCESS_CODE) : Call<T> {

    override fun execute(): Response<T> {
        if (response != null) {
            val headers = Headers.Builder().add("Authorization", "123").build()
            val success = Response.success(response, headers)
            return success
        } else throw Throwable("response was null!")
    }

    override fun enqueue(callback: Callback<T>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isExecuted(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clone(): Call<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCanceled(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun request(): Request {
        return Request.Builder().url("")
            .build()
    }
}
