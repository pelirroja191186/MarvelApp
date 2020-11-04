package com.yhq.marvel.core.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.common.truth.Truth
import com.yhq.marvel.core.result.Failure
import com.yhq.marvel.core.result.Failure.Unauthorized
import org.junit.*

class BaseViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `handling failure ok`() {
        val viewModel = ViewModelTest()

        viewModel.handleError(Unauthorized)

        val failure = viewModel.failure
        val error = viewModel.failure.value

        Truth.assertThat(failure).isInstanceOf(MutableLiveData::class.java)
        Truth.assertThat(error).isInstanceOf(Unauthorized::class.java)
    }

    private class ViewModelTest : BaseViewModel() {
        fun handleError(failure: Failure) = handleFailure(failure)
    }
}
