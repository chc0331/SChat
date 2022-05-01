package com.example.myapplication.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.rxjava3.core.*

interface UserDataRepository {

    fun initFriendData(email: String)

    fun initUserData()

    fun addFriend(friend: Friend): Completable

    fun getFriend(email: String, result: MutableLiveData<Friend>)

    fun getAllFriends(): Flowable<List<Friend>>

    fun addUser(user: User)

    fun getUserByUuid(uuid: String): Task<DocumentSnapshot>

    //    fun getUserByEmail(email: String): Maybe<User>
    fun getUserByEmail(email: String): Task<QuerySnapshot>


    fun getAllUsers(): Flowable<List<User>>

    fun deleteAllUsers()

    fun deleteAllFriends()

    fun updateUser(user: User)

    fun updateFriend(friend: Friend)
}