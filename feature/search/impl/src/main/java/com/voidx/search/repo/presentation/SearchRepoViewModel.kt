package com.voidx.search.repo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voidx.github.core.data.ext.appendItems
import com.voidx.github.core.presentation.State
import com.voidx.github.utility.data.ext.disposedBy
import com.voidx.repo.model.RepoDTO
import com.voidx.search.repo.domain.SearchRepoUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchRepoViewModel(
    private val useCase: SearchRepoUseCase
) : ViewModel() {

    private val state = MutableLiveData<State>()
    private val results = MutableLiveData<MutableList<RepoDTO>>(mutableListOf())

    private val disposables = CompositeDisposable()

    fun state(): LiveData<State> = state

    fun results(): LiveData<List<RepoDTO>> {
        return results as? LiveData<List<RepoDTO>> ?: MutableLiveData(emptyList())
    }

    fun reload() {
        results.postValue(mutableListOf())
        useCase.resetSearchPaging()
        load()
    }

    fun load() {

        state.postValue(State.Loading)

        useCase
            .searchRepo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it.isEmpty()) {
                    if (useCase.isFirstPage()) {
                        state.postValue(State.Empty)
                    } else {
                        state.postValue(State.Success)
                    }
                    return@subscribe
                }

                results.appendItems(it)
                state.postValue(State.Success)
            }, {
                state.postValue(State.Error)
            })
            .disposedBy(disposables)

    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

}