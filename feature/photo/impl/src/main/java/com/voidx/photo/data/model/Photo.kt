package com.voidx.photo.data.model

import com.google.gson.annotations.SerializedName

data class Photo(

    @SerializedName("title")
    val title: String?,

    @SerializedName("url_l")
    val url: String?
)
