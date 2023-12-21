package com.example.stylesync.ui.login

import androidx.lifecycle.ViewModel
import com.example.stylesync.data.preference.UserModel
import com.example.stylesync.data.remote.response.LoginResponse
import com.example.stylesync.data.remote.response.RegisterResponse
import com.example.stylesync.repository.Repository

class LoginViewModel (private val repository: Repository) : ViewModel() {
    suspend fun login(email: String, password: String): LoginResponse {
        return repository.login(email, password)
    }

    suspend fun saveSession(user: UserModel) {
        repository.saveSession(user)
    }
}