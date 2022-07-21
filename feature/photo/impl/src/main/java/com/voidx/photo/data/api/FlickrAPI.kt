package com.voidx.photo.data.api

import io.reactivex.rxjava3.core.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface FlickrAPI {

    @GET
    fun get(
        @Url url: String,
        @Query("method") method: String,
        @Query("page") page: Int
    ): Observable<Response<String>>
}
