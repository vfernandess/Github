package com.voidx.repo.model

import android.os.Parcel
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.voidx.repo.BR
import com.voidx.user.domain.model.UserDTO

class RepoDTO() : BaseObservable(), Parcelable {

    var id: Int = -1

    @Bindable
    var name: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }

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

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString()
        description = parcel.readString()
        forksCount = parcel.readInt()
        starsCount = parcel.readInt()
        owner = parcel.readParcelable(UserDTO::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RepoDTO> {
        override fun createFromParcel(parcel: Parcel): RepoDTO {
            return RepoDTO(parcel)
        }

        override fun newArray(size: Int): Array<RepoDTO?> {
            return arrayOfNulls(size)
        }
    }

}