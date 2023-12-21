package com.example.stylesync.data.remote

import android.content.Context
import android.util.Log
import com.example.stylesync.data.preference.UserPreference
import com.example.stylesync.data.preference.dataStore
import com.example.stylesync.repository.Repository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        Log.d("TAG", "provideRepository: ${user.token}")
        val apiService = ApiConfig.getApiService(pref)
        return Repository.getInstance(pref, apiService)
    }
}