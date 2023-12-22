package com.example.stylesync.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylesync.data.preference.UserModel
import com.example.stylesync.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {
    private val _sessionLiveData = MutableLiveData<UserModel>()
    val sessionLiveData: LiveData<UserModel> get() = _sessionLiveData

    fun getSession() {
        viewModelScope.launch {
            repository.getSession().collect { userSession ->
                _sessionLiveData.value = userSession
            }
        }
    }
}
