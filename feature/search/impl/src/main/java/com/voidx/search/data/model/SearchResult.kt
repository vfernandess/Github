package com.voidx.search.data.model

import com.google.gson.annotations.SerializedName

open class SearchResult<T>(

    @SerializedName("items")
    var items: List<T>,

    var hasNextPage: Boolean

)