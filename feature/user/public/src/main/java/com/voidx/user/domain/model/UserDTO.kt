package com.voidx.user.domain.model

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.voidx.user.BR

class UserDTO() : BaseObservable(), Parcelable {

    var id: Int = -1

    @Bindable
    var login: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.login)
        }

    @Bindable
    var avatar: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.avatar)
        }

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        login = parcel.readString()
        avatar = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(login)
        parcel.writeString(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserDTO> {
        override fun createFromParcel(parcel: Parcel): UserDTO {
            return UserDTO(parcel)
        }

        override fun newArray(size: Int): Array<UserDTO?> {
            return arrayOfNulls(size)
        }
    }

}