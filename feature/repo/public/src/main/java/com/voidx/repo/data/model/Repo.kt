package com.voidx.repo.data.model

import com.google.gson.annotations.SerializedName
import com.voidx.user.data.model.User

data class Repo(

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String?,

    @SerializedName("forks_count")
    val forksCount: Int,

    @SerializedName("stargazers_count")
    val starsCount: Int,

    @SerializedName("owner")
    val owner: User
)