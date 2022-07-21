package com.voidx.pull.repo.domain.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.voidx.pull.impl.BR
import com.voidx.user.domain.model.UserDTO
import java.util.*

class PullRequestDTO : BaseObservable() {

    @Bindable
    var title: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    @Bindable
    var description: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    @Bindable
    var url: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.url)
        }


    @Bindable
    var createdAt: Date = Date()
        set(value) {
            field = value
            notifyPropertyChanged(BR.createdAt)
        }

    @Bindable
    var owner: UserDTO? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.owner)
        }
}
