package com.example.stylesync.data.remote.response

import com.google.gson.annotations.SerializedName

data class ChangeFavResponse(

	@field:SerializedName("stauts")
	val stauts: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)
