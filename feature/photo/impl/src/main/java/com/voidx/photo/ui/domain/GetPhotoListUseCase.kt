package com.voidx.photo.ui.domain

import com.voidx.photo.data.repository.PhotoListRepository
import com.voidx.photo.ui.domain.model.PhotoDTO
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 10

internal interface GetPhotoListUseCase {

    fun getPhotos(): Observable<List<PhotoDTO>>

    class Impl @Inject constructor(
        private val repository: PhotoListRepository,
        private val mapper: PhotoDtoMapper
    ): GetPhotoListUseCase {

        private var page = 1
        private var maxPage = Int.MAX_VALUE

        override fun getPhotos(): Observable<List<PhotoDTO>> {
            if(page > maxPage) {
                return Observable.just(emptyList())
            }

            return repository
                .getPhotos(page)
                .map {
                    page += 1

                    maxPage = it.photos.pages
                    it
                        .photos
                        .photos
                        .map(mapper::map)
                }
        }
    }
}
