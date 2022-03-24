package com.example.myapplication.toyproject.login.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

fun FirebaseAuth?.isLogged(): Boolean = this?.currentUser != null

fun FirebaseAuth?.getCurrentUserName(): String = this?.currentUser?.displayName.toString()

fun FirebaseAuth?.getCurrentUserEmail(): String = this?.currentUser?.email.toString()
