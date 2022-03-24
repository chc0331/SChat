package com.example.myapplication.data.model

import android.os.Parcel
import android.os.Parcelable

data class ParcelableUser(
    var name: String,
    var phone: String,
    var id: String,
    var password: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(id)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun parcelableToUser(): User {
        return User(name, phone, id, password)
    }

    companion object CREATOR : Parcelable.Creator<ParcelableUser> {
        override fun createFromParcel(parcel: Parcel): ParcelableUser {
            return ParcelableUser(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableUser?> {
            return arrayOfNulls(size)
        }

        fun userToParcelable(user: User): ParcelableUser {
            return ParcelableUser(user.name, user.phone, user.email, user.password)
        }

    }

}
