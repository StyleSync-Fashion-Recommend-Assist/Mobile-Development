package com.example.stylesync.data.remote.response

import com.google.gson.annotations.SerializedName

data class AddItemResponse(

	@field:SerializedName("data")
	val data: data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class data(

	@field:SerializedName("itemId")
	val itemId: Int? = null,

	@field:SerializedName("kategoriId")
	val kategoriId: Int? = null,

	@field:SerializedName("warnaId")
	val warnaId: Int? = null,

	@field:SerializedName("warna")
	val warna: String? = null,

	@field:SerializedName("occupation")
	val occupation: String? = null,

	@field:SerializedName("photoImage")
	val photoImage: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("subKategoriId")
	val subKategoriId: Int? = null,

	@field:SerializedName("userId")
	val userId: Int? = null,

	@field:SerializedName("namaItem")
	val namaItem: String? = null
)
