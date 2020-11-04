package com.yhq.marvel.core.base

import androidx.lifecycle.ViewModel
import com.yhq.marvel.core.result.Failure
import com.yhq.marvel.core.utils.SingleLiveEvent

open class BaseViewModel : ViewModel() {
    var failure: SingleLiveEvent<Failure> = SingleLiveEvent()

    protected fun handleFailure(failure: Failure) {
        this.failure.postValue(failure)
    }
}
