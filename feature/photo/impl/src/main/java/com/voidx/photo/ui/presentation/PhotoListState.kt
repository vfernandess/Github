package com.voidx.photo.ui.presentation

import com.voidx.photo.ui.domain.model.PhotoDTO

sealed class PhotoListState {

    data class ShowPhotoList(
        val photos: List<PhotoDTO>
    ): PhotoListState()

    object Loading : PhotoListState()

    object Empty : PhotoListState()

    object Error : PhotoListState()
}
