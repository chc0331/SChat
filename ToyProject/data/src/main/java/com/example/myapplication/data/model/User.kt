package com.example.myapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "phone")
    var phone: String = "",
    @PrimaryKey
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "password")
    var password: String = ""
) {

    fun toMap(): HashMap<String, String> {
        return hashMapOf(
            "name" to name,
            "phone" to phone,
            "id" to email,
            "password" to password
        )
    }
}