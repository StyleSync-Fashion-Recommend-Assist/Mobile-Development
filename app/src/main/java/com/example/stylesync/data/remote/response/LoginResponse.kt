package com.example.stylesync.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("AccessToken")
    val AccessToken: String? = null
)