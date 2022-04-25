package com.example.myapplication.toyproject.core

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

abstract class FireBaseViewModel : ViewModel() {
    val auth: FirebaseAuth = Firebase.auth

    fun getUserName(): String {
        return auth.currentUser?.displayName ?: "nothing"
    }

    fun getUserEmail(): String {
        return auth.currentUser?.email ?: "nothing"
    }

    fun getUserUUID(): String = auth.currentUser!!.uid
}