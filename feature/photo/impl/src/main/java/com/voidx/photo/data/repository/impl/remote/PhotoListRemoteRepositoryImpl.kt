package com.voidx.photo.data.repository.impl.remote

import com.voidx.photo.data.api.FlickrAPI
import com.voidx.photo.data.model.ResultPhotos
import com.voidx.photo.data.repository.PhotoListRepository
import com.voidx.photo.data.repository.impl.remote.mapper.PhotoListRepositoryMapper
import io.reactivex.rxjava3.core.Observable

const val FLICKR_API_URL = "https://api.flickr.com/services/rest/?api_key=b58aa44775814c3d2fda2c6951068105&extras=url_l&format=json&per_page=10"
const val GET_PHOTO_LIST = "flickr.interestingness.getList"

class PhotoListRemoteRepositoryImpl(
    private val api: FlickrAPI,
    private val mapper: PhotoListRepositoryMapper
): PhotoListRepository {

    override fun getPhotos(page: Int): Observable<ResultPhotos> {
        return api
            .get(FLICKR_API_URL, GET_PHOTO_LIST, page)
            .map { response ->
                return@map response.body()?.let {
                    mapper.map(it)
                } ?: throw NullPointerException()
            }
    }
}
