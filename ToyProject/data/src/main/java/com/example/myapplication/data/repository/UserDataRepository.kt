package com.example.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import io.reactivex.rxjava3.core.*

interface UserDataRepository {

    fun initFriendData(email: String)

    fun initUserData()

    fun addFriend(friend: Friend): Completable

    fun getFriend(email: String, result: MutableLiveData<Friend>)

    fun getAllFriends(): Flowable<List<Friend>>

    fun addUser(user: User)

    fun getUser(email: String): Maybe<User>

    fun getAllUsers()

    fun deleteAllUsers()

    fun deleteAllFriends()

    fun updateFriend(friend: Friend)
}