package com.voidx.pull.data.model

import com.google.gson.annotations.SerializedName
import com.voidx.user.data.model.User
import java.util.Date

data class PullRequest(

    @SerializedName("user")
    val owner: User,

    @SerializedName("title")
    val title: String,

    @SerializedName("body")
    val description: String?,

    @SerializedName("created_at")
    val createdAt: Date,

    @SerializedName("html_url")
    val url: String
)