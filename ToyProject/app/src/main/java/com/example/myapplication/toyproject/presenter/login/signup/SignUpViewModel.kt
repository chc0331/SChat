package com.example.myapplication.toyproject.presenter.login.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.toyproject.core.FireBaseViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.ktx.Firebase

class SignUpViewModel(private val repository: UserDataRepository) : FireBaseViewModel() {
    private val _registerSuccess = MutableLiveData<String>()
    val registerSuccess: LiveData<String>
        get() = _registerSuccess

    var name: String = ""
    var phone: String = ""
    var id: String = ""
    var password: String = ""

    fun createAccount() {
        if (name.isNotEmpty() && phone.isNotEmpty() &&
            id.isNotEmpty() && password.isNotEmpty()
        ) {
            try {
                auth?.createUserWithEmailAndPassword(id, password)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Firebase.auth.currentUser?.updateProfile(userProfileChangeRequest {
                                displayName = name
                            })?.addOnCompleteListener { task2 ->
                                if (task2.isSuccessful) {
                                    repository.addUser(
                                        User(name, phone, id, password),
                                        _registerSuccess
                                    )
                                    _registerSuccess.postValue(getMessage(task))
                                }
                            }
                        }
                    }
            } catch (e: FirebaseAuthException) {
                Log.d("heec.choi", e.errorCode)
                _registerSuccess.postValue("계정 생성 실패")
            }
        } else {
            _registerSuccess.postValue("빈칸이 있습니다.")
        }
    }

    private fun getMessage(task: Task<AuthResult>): String {
        return if (task.isSuccessful)
            "계정 생성 성공"
        else {
            when (task.exception) {
                is FirebaseAuthUserCollisionException -> "아이디(이메일)가 중복됩니다."
                is FirebaseAuthWeakPasswordException -> "비밀번호는 최소 6글자 이상이여야 합니다."
                else -> "계정 생성 실패"
            }
        }
    }

    fun checkDuplicatedId(id: String) {
        //todo
    }
}