package com.voidx.user.domain.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.voidx.user.BR

class UserDTO : BaseObservable() {

    var id: Int = -1

    @Bindable
    var login: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.login)
        }

    @Bindable
    var avatar: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.avatar)
        }
}