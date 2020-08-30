package com.voidx.github.core.lifecycle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class DefaultViewModelProviderFactory @Inject constructor(
    private val viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModels[modelClass]?.get()?.let {
            @Suppress("UNCHECKED_CAST")
            it as? T ?: throw IllegalStateException("ViewModel [$it] not a type of $modelClass")
        } ?: throw IllegalStateException("No ViewModel found for $modelClass")
    }
}