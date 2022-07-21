package com.voidx.photo.ui.domain

import com.voidx.photo.data.model.Photo
import com.voidx.photo.ui.domain.model.PhotoDTO
import javax.inject.Inject

interface PhotoDtoMapper {

    fun map(photo: Photo): PhotoDTO

    class Impl @Inject constructor() : PhotoDtoMapper {
        override fun map(photo: Photo): PhotoDTO {
            return PhotoDTO().apply {
                title = photo.title
                url = photo.url
            }
        }
    }
}
