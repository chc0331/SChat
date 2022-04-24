package com.example.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "uuid")
    var uuid: String = "",
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "phone")
    var phone: String = "",
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "password")
    var password: String = "",
    @ColumnInfo(name = "image")
    var image: String = ""
) {

    fun toMap(): HashMap<String, String> {
        return hashMapOf(
            "uuid" to uuid,
            "name" to name,
            "phone" to phone,
            "id" to email,
            "password" to password,
            "image" to image
        )
    }
}