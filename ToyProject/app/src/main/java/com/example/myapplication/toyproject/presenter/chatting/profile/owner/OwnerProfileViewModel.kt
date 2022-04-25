package com.example.myapplication.toyproject.presenter.chatting.profile.owner

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.toyproject.core.FireBaseViewModel

class OwnerProfileViewModel(private val repository: UserDataRepository) : FireBaseViewModel() {
    private val TAG: String = OwnerProfileViewModel::class.java.simpleName
    private var _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User>
        get() = _user

    fun updateUserProfile() {
        Log.d("heec.choi", getUserUUID())
        repository.getUserByUuid(getUserUUID()).doOnError {
            Log.d(TAG, "getUserByUUID onError")
        }.doOnComplete {
            Log.d(TAG, "getUserByUUID onComplete")
        }.doOnSuccess {
            _user.postValue(it)
            Log.d(TAG, "getUserByUUID onSuccess")
        }.subscribe()
    }

}