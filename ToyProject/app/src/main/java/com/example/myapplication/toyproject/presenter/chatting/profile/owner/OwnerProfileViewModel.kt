package com.example.myapplication.toyproject.presenter.chatting.profile.owner

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.toyproject.core.FireBaseViewModel
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class OwnerProfileViewModel(private val repository: UserDataRepository) : FireBaseViewModel() {
    private val TAG: String = OwnerProfileViewModel::class.java.simpleName
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
    private var _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User>
        get() = _user
    var imageUri: String = ""

    fun updateUserProfile() {
        repository.getUserByUuid(getUserUUID()).doOnError {
            Log.d(TAG, "getUserByUUID onError")
        }.doOnComplete {
            Log.d(TAG, "getUserByUUID onComplete")
        }.doOnSuccess {
            _user.postValue(it)
            Log.d(TAG, "getUserByUUID onSuccess")
        }.subscribe()
    }

    fun changeUserProfile(context: Context, name: String, phone: String, password: String) {
        if (name.isNotEmpty() && phone.isNotEmpty()
            && password.isNotEmpty() && password.length >= 6
        ) {
            val user = _user.value
            user!!.also {
                it.name = name
                it.phone = phone
                it.password = password
            }
            repository.updateUser(user)
            Toast.makeText(context, "Profile Update", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateImageDatabase(uri: Uri?) {
        uri?.apply {
            val fileName = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
            storage.reference.child("image").child(fileName)
                .putFile(uri)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUrl = task.result
                        val url = downloadUrl.toString()
                    }
                }
        }
    }
}