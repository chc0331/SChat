package com.example.myapplication.toyproject.login.core

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

abstract class FireBaseViewModel : ViewModel() {
    val auth: FirebaseAuth = Firebase.auth
    val fireStore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getUserName(): String {
        return auth.currentUser?.displayName ?: "nothing"
    }

    fun getUserEmail(): String {
        return auth.currentUser?.email ?: "nothing"
    }
}