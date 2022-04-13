package com.example.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import io.reactivex.rxjava3.core.Flowable

interface UserDataRepository {

    fun initData(email: String)

    fun addFriend(friend: Friend, result: MutableLiveData<Boolean>)

    fun getFriend(email: String, result: MutableLiveData<Friend>)

    fun getAllFriends(): Flowable<List<Friend>>

    fun addUser(user: User)

    fun getUser(email: String, result: MutableLiveData<User>)

    fun getUsers()

    fun deleteUsers()

    fun deleteAllFriends()

    fun updateFriend(friend: Friend)
}