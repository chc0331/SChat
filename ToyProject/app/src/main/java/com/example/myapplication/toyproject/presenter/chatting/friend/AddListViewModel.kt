package com.example.myapplication.toyproject.presenter.chatting.friend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.toyproject.core.FireBaseViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AddListViewModel(private val repository: UserDataRepository) : FireBaseViewModel() {
    private val currentUser: FirebaseUser? = Firebase.auth.currentUser
    val editEmail: MutableLiveData<String> = MutableLiveData()

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean>
        get() = _success

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun addFriend() {
        if (currentUser == null) return
        editEmail.value?.let { it ->
            repository.addFriend(Friend(ownerEmail = getUserEmail(), friendEmail = it), _success)
        }
    }
}