package com.example.myapplication.toyproject.presenter.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.toyproject.core.FireBaseViewModel

class LoginViewModel(private val userDataRepository: UserDataRepository) : FireBaseViewModel() {
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    fun login(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener { task ->
                    _loginSuccess.postValue(task.isSuccessful)
                    userDataRepository.initFriendData(email)
                }
        } else
            _loginSuccess.postValue(false)
    }

    fun initUsers(){
        userDataRepository.initUserData()
    }

}