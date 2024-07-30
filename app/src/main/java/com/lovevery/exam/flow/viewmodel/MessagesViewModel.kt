package com.lovevery.exam.flow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.lovevery.exam.base.viewmodel.BaseViewModel
import com.lovevery.exam.utils.extensions.asLiveData
import com.lovevery.exam.utils.extensions.asValue
import javax.inject.Inject

class MessagesViewModel @Inject constructor(): BaseViewModel() {

    private val newUserNameValue = MutableLiveData<String>()

    private val isButtonAddUserEnable = MediatorLiveData<Boolean>().apply {
        addSource(newUserNameValue) { value = newUserNameValue.asValue().isNotEmpty() }
    }
    fun getButtonAddUserEnable() = isButtonAddUserEnable.asLiveData()

    fun setNewUserNameValue(name: String) {
        newUserNameValue.value = name
    }
}