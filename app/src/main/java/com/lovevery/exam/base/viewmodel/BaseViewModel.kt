package com.lovevery.exam.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    val disposable = CompositeDisposable()

    protected val showMessage = SingleLiveEvent<Int>()
    protected val showMessageText = SingleLiveEvent<String>()
    protected val showError = SingleLiveEvent<Int>()
    protected val showErrorMessage = SingleLiveEvent<String>()

    fun getShowMessage(): LiveData<Int> = showMessage
    fun getShowMessageText(): LiveData<String> = showMessageText
    fun getShowError(): LiveData<Int> = showError
    fun getShowErrorMessage(): LiveData<String> = showErrorMessage

    override fun onCleared() {
        disposable.clear()
    }
}