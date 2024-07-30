package com.lovevery.exam.utils.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun <T> MutableLiveData<T>.asValue() = this.value.toString()