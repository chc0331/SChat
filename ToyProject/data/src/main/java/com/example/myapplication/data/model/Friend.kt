package com.example.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "friends")
data class Friend(
    @ColumnInfo(name = "owner_uuid")
    var ownerUuid: String = "",
    @PrimaryKey
    @ColumnInfo(name = "friend_uuid")
    var friendUuid: String = "",
    @ColumnInfo(name = "friend_email")
    var friendEmail: String = "",
    @ColumnInfo(name = "friend_name")
    var friendName: String = "",
    @ColumnInfo(name = "friend_phone")
    var friendPhone: String = ""
) {

    fun toMap(): HashMap<String, String> {
        return hashMapOf(
            "friendUuid" to friendUuid,
            "ownerUuid" to ownerUuid,
            "friendEmail" to friendEmail,
            "friendName" to friendName,
            "friendPhone" to friendPhone
        )
    }
}
