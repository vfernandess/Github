package com.voidx.photo.data.repository.impl.remote.mapper

import com.google.gson.Gson
import com.voidx.photo.data.api.ResponseApiMapper
import com.voidx.photo.data.api.normalizeJSON
import com.voidx.photo.data.model.ResultPhotos
import javax.inject.Inject

interface PhotoListRepositoryMapper: ResponseApiMapper<ResultPhotos> {

    class Impl @Inject constructor(
        private val gson: Gson
    ) : PhotoListRepositoryMapper {
        override fun map(json: String): ResultPhotos {
            val normalizedJSON = this.normalizeJSON(json)
            return gson.fromJson(normalizedJSON, ResultPhotos::class.java)
        }
    }
}
