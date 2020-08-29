package com.voidx.github.core.data.ext

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<MutableList<T>>.appendItems(values: List<T>) {
    val value = this.value ?: arrayListOf()
    value.addAll(values)
    postValue(value)
}