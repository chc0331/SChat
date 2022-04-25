package com.example.myapplication.toyproject.presenter.chatting.profile.owner

import android.net.Uri
import android.util.Log
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

    fun changeUserProfile(name: String, phone: String, password: String) {
        Log.d(
            "heec.choi",
            "imageUri : $imageUri, name : $name, phone : $phone, password : $password"
        )
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