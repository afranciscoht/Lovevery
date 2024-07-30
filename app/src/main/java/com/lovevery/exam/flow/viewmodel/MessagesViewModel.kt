package com.lovevery.exam.flow.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.lovevery.exam.base.viewmodel.BaseViewModel
import com.lovevery.exam.flow.action.MessagesAction
import com.lovevery.exam.utils.extensions.asLiveData
import com.lovevery.exam.utils.extensions.asValue
import javax.inject.Inject

class MessagesViewModel @Inject constructor() : BaseViewModel() {

    private val newUserNameValue = MutableLiveData<String>()
    private val listUserAdded = mutableListOf<String>()

    private val action = MutableLiveData<MessagesAction>()
    fun getAction() = action.asLiveData()

    private val isButtonAddUserEnable = MediatorLiveData<Boolean>().apply {
        addSource(newUserNameValue) { value = newUserNameValue.asValue().length > 3 }
    }

    fun getButtonAddUserEnable() = isButtonAddUserEnable.asLiveData()

    fun setNewUserNameValue(name: String) {
        newUserNameValue.value = name
    }

    fun addUser(name: String) {
        if (listUserAdded.indexOf(name) == -1) {
            listUserAdded.add(name)
        }

        action.value = MessagesAction.ShowUserList(listUserAdded)
    }
}