package com.voidx.core.data.ext

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.disposables.DisposableContainer

fun Disposable.disposedBy(container: DisposableContainer) {
    container.add(this)
}

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}
