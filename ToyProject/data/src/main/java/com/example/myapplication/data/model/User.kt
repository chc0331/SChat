package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    var uuid: String = "",
    var name: String = "",
    var phone: String = "",
    var email: String,
    var password: String = "",
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