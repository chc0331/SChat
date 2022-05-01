package com.example.myapplication.data.model

import android.os.Parcel
import android.os.Parcelable

class ParcelableFriend(
    var ownerEmail: String?,
    var friendUuid: String?,
    var friendEmail: String?,
    var friendName: String?,
    var friendPhone: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ownerEmail)
        parcel.writeString(friendUuid)
        parcel.writeString(friendEmail)
        parcel.writeString(friendName)
        parcel.writeString(friendPhone)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun makeFriend(): Friend {
        return Friend(ownerEmail!!, friendEmail!!, friendName!!, friendPhone!!)
    }

    companion object CREATOR : Parcelable.Creator<ParcelableFriend> {
        override fun createFromParcel(parcel: Parcel): ParcelableFriend {
            return ParcelableFriend(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableFriend?> {
            return arrayOfNulls(size)
        }

        fun friendToParcelable(friend: Friend): ParcelableFriend {
            return ParcelableFriend(
                friend.ownerUuid,
                friend.friendUuid,
                friend.friendEmail,
                friend.friendName,
                friend.friendPhone
            )
        }
    }


}