package com.example.stylesync.data.remote

import com.example.stylesync.data.remote.request.Items
import com.example.stylesync.data.remote.response.AddItemResponse
import com.example.stylesync.data.remote.response.ChangeFavResponse
import com.example.stylesync.data.remote.response.LoginResponse
import com.example.stylesync.data.remote.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("closet/addItem")
    suspend fun addItem(
        @Field("userId") userId: Int,
        @Field("kategoriId") kategoriId: Int,
        @Field("subKategoriId") subKategoriId: Int,
        @Field("warnaId") warnaId: Int,
        @Field("items") items: Items
    ): AddItemResponse

    @FormUrlEncoded
    @POST("closet/changeFav")
    suspend fun changeFav(
        @Field("id") id: Int,
        @Field("userId") userId: Int
    ): ChangeFavResponse
}