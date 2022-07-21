package com.voidx.photo.ui.domain.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.voidx.photo.impl.BR

class PhotoDTO : BaseObservable() {

    @Bindable
    var title: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @Bindable
    var url: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.url)
        }

    override fun equals(other: Any?): Boolean {
        val another = other as? PhotoDTO ?: return false

        if (another.title != title) {
            return false
        }

        if (another.url != url) {
            return false
        }

        return true
    }

    override fun hashCode(): Int {
        var hash = 7
        hash += 31 * (title?.hashCode() ?: 0)
        hash += 31 * (url?.hashCode() ?: 0)
        return hash
    }
}
