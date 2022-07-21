package com.voidx.photo.data.api

interface ResponseApiMapper<T> {

    fun map(json: String): T
}

fun ResponseApiMapper<*>.normalizeJSON(json: String): String {
    var normalizer = json.replace("jsonFlickrApi(", "")
    normalizer = normalizer.substring(0, normalizer.length - 1)
    return normalizer
}
