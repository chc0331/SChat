package com.example.myapplication.data

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import com.google.firebase.firestore.DocumentSnapshot

fun MutableLiveData<List<Friend>>.set(value: List<Friend>): MutableLiveData<List<Friend>> {
    this.value = value
    return this
}

fun MutableLiveData<String>.set(value: String): MutableLiveData<String> {
    this.value = value
    return this
}

fun Map<String, Object>.toUser(): User = User(
    this["uuid"].toString(), this["name"].toString(),
    this["phone"].toString(), this["email"].toString(),
    this["password"].toString(), this["image"].toString()
)

fun Map<String, Object>.toFriend(): Friend = Friend(
    this["ownerEmail"].toString(), this["friendEmail"].toString(),
    this["friendName"].toString(), this["friendPhone"].toString()
)

fun DocumentSnapshot.toUser(): User = User(
    this["name"].toString(), this["phone"].toString(),
    this["id"].toString(), this["password"].toString()
)