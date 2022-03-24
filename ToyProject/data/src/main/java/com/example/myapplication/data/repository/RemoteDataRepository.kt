package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

interface RemoteDataRepository {
    var fireStore: FirebaseFirestore

    fun addFriend(friend: Friend): Task<Void>

    fun getFriend(email: String): Task<QuerySnapshot>

    fun addUser(user: User): Task<DocumentReference>

    fun getUser(email: String): Task<QuerySnapshot>

    fun getAllUsers(): Task<QuerySnapshot>

    fun getAllFriends(): Task<QuerySnapshot>

    fun updateFriend(friend: Friend): Task<Void>
}