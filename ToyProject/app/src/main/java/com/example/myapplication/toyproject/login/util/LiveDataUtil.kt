package com.example.myapplication.toyproject.login.util

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend

fun MutableLiveData<Friend?>.set(friend: Friend?): MutableLiveData<Friend?> {
    this.value = friend
    return this
}