package com.voidx.photo.ui.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voidx.core.data.ext.plusAssign
import com.voidx.photo.ui.domain.GetPhotoListUseCase
import com.voidx.photo.ui.domain.model.PhotoDTO
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

internal class PhotoListViewModel @Inject constructor(
    private val useCase: GetPhotoListUseCase,
    private val mainThreadScheduler: Scheduler
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private var photoCount = 0

    private val state = MutableLiveData<PhotoListState>()

    fun state(): LiveData<PhotoListState> = state

    fun load() {

        state.postValue(PhotoListState.Loading)

        disposables += useCase
            .getPhotos()
            .observeOn(mainThreadScheduler)
            .subscribeOn(Schedulers.io())
            .subscribe(
                ::onPhotosRetrieved
            ) {
                state.postValue(PhotoListState.Error)
            }
    }

    private fun onPhotosRetrieved(photos: List<PhotoDTO>) {
        if (photoCount == 0 && photos.isEmpty()) {
            state.postValue(PhotoListState.Empty)
            return
        }

        photoCount = photos.size
        state.postValue(PhotoListState.ShowPhotoList(photos))
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}
