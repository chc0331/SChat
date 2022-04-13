package com.example.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface LocalDataRepository {

    fun addFriend(friend: Friend)

    fun getFriend(email: String, friends: MutableLiveData<Friend>)

    fun getAllFriends(): Flowable<List<Friend>>

    fun addUser(user: User)

    fun getUser(email: String, user: MutableLiveData<User>)

    fun getUsers()

    fun deleteUsers()

    fun deleteAllFriends(): Completable

    fun updateFriend(friend: Friend)

}