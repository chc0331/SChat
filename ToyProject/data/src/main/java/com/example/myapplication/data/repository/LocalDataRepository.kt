package com.example.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import io.reactivex.rxjava3.core.Completable

interface LocalDataRepository {

    fun addFriend(friend: Friend)

    fun getFriend(email: String, friends: MutableLiveData<Friend>)

    fun getAllFriends(result: MutableLiveData<List<Friend>>)

    fun addUser(user: User)

    fun getUser(email: String, user: MutableLiveData<User>)

    fun getUsers()

    fun deleteUsers()

    fun deleteFriends(): Completable

    fun updateFriend(friend: Friend)

}