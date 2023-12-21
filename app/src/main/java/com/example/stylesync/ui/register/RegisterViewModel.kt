package com.example.stylesync.ui.register

import androidx.lifecycle.ViewModel
import com.example.stylesync.data.remote.response.RegisterResponse
import com.example.stylesync.repository.Repository

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        return repository.register(name, email, password)
    }
}