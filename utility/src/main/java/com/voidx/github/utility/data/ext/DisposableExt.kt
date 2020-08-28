package com.voidx.github.utility.data.ext

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.disposables.DisposableContainer

fun Disposable.disposedBy(container: DisposableContainer) {
    container.add(this)
}