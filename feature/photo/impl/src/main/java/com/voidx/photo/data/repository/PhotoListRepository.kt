package com.voidx.photo.data.repository

import com.voidx.photo.data.model.ResultPhotos
import io.reactivex.rxjava3.core.Observable

interface PhotoListRepository {

    fun getPhotos(page: Int): Observable<ResultPhotos>
}
