package com.example.myapplication.toyproject.presenter.chatting.profile.friend

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.toyproject.core.FireBaseViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AddListViewModel(private val repository: UserDataRepository) : FireBaseViewModel() {
    private val currentUser: FirebaseUser? = Firebase.auth.currentUser

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    val success: LiveData<Boolean>
        get() = _success

    fun addFriend(email: String) {
        if (currentUser == null) return

        //find friend
        repository.getUserByEmail(email)
            .doOnError { Log.e("heec.choi", "onError") }
            .doOnComplete {
                _success.postValue(false)
                Log.d("heec.choi", "onComplete")
            }.doOnSuccess {
                val friend = Friend(getUserEmail(), it.email, it.name, it.phone)
                //add friend
                repository.addFriend(friend).subscribe {
                    _success.postValue(true)
                }
                Log.d("heec.choi", "success $it")
            }.subscribe()
    }
}