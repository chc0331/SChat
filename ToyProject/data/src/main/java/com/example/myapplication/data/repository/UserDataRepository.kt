package com.example.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User

interface UserDataRepository {

    fun initData(email: String)

    fun addFriend(friend: Friend, result: MutableLiveData<Boolean>)

    fun getFriend(email: String, result: MutableLiveData<Friend>)

    fun getAllFriends(result: MutableLiveData<List<Friend>>)

    fun addUser(user: User, result: MutableLiveData<String>)

    fun getUser(email: String, result: MutableLiveData<User>)

    fun getUsers()

    fun deleteUsers()

    fun deleteFriends()

    fun updateFriend(friend: Friend)
}