package com.voidx.pull.repo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.voidx.github.core.presentation.State
import com.voidx.github.utility.data.ext.disposedBy
import com.voidx.pull.repo.domain.RepoPullRequestUseCase
import com.voidx.pull.repo.domain.model.PullRequestDTO
import com.voidx.repo.model.RepoDTO
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RepoPullRequestViewModel @Inject constructor(
    private val useCase: RepoPullRequestUseCase,
    private val mainThreadScheduler: Scheduler
) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val prs = MutableLiveData<List<PullRequestDTO>>(emptyList())

    private val state = MutableLiveData<State>()

    fun prs(): LiveData<List<PullRequestDTO>> = prs

    fun state(): LiveData<State> = state

    fun load(repo: RepoDTO) {

        state.postValue(State.Loading)

        useCase
            .getPullRequestsFromRepo(repo)
            .observeOn(mainThreadScheduler)
            .subscribeOn(Schedulers.io())
            .subscribe({
                if(it.isEmpty()) {
                    state.postValue(State.Empty)
                } else {
                    state.postValue(State.Success)
                }

                prs.postValue(it)
            }, {
                prs.postValue(emptyList())
                state.postValue(State.Error)
            })
            .disposedBy(disposables)
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }
}