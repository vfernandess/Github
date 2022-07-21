package com.voidx.photo.data.model

import com.google.gson.annotations.SerializedName

data class ResultPhotos(

    @SerializedName("photos")
    val photos: ResultPhoto
)

data class ResultPhoto(

    @SerializedName("photo")
    val photos: List<Photo>,

    @SerializedName("pages")
    val pages: Int
)
