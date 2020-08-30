package com.voidx.repo.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.voidx.repo.BR
import com.voidx.user.domain.model.UserDTO

class RepoDTO : BaseObservable() {

    var id: Int = -1

    @Bindable
    var name: String = ""

    @Bindable
    var description: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.description)
        }

    @Bindable
    var forksCount: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.forksCount)
        }

    @Bindable
    var starsCount: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.starsCount)
        }

    @Bindable
    var owner: UserDTO? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.owner)
        }

}