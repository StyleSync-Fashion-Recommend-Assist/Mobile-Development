package com.example.stylesync.data.remote

import com.example.stylesync.data.preference.UserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(pref: UserPreference): ApiService {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val authInterceptor = Interceptor { chain ->
                val req = chain.request()
                val token = runBlocking{ pref.getSession().first()}
                val requestHeaders =
                    req.newBuilder().addHeader("Authorization", "Bearer ${token.token}").build()
                chain.proceed(requestHeaders)
            }
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor).build()
            val retrofit = Retrofit.Builder().baseUrl("https://stylesync-run-t4yhz77ada-de.a.run.app/api/")
                .addConverterFactory(GsonConverterFactory.create()).client(client).build()
            return retrofit.create(ApiService::class.java)
        }
    }
}