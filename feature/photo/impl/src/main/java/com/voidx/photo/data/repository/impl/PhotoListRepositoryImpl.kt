package com.voidx.photo.data.repository.impl

import com.voidx.photo.data.model.ResultPhotos
import com.voidx.photo.data.repository.PhotoListRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PhotoListRepositoryImpl @Inject constructor(
    private val localRepository: PhotoListRepository
): PhotoListRepository {

    override fun getPhotos(page: Int): Observable<ResultPhotos> {
        return localRepository.getPhotos(page)
    }
}
