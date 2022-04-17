package com.example.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import io.reactivex.rxjava3.core.*

interface LocalDataRepository {

    fun addFriend(friend: Friend): Completable

    fun getFriend(email: String, friends: MutableLiveData<Friend>)

    fun getAllFriends(): Flowable<List<Friend>>

    fun addUser(user: User): Completable

    fun getUser(email: String): Maybe<User>

    fun getAllUsers(): Flowable<List<User>>

    fun deleteAllUsers(): Completable

    fun deleteAllFriends(): Completable

    fun updateFriend(friend: Friend)

}