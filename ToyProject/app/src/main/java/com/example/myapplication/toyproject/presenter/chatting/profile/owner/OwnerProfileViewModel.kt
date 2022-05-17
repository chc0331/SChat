package com.example.myapplication.toyproject.presenter.chatting.profile.owner

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.data.toUser
import com.example.myapplication.toyproject.core.FireBaseViewModel
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class OwnerProfileViewModel(private val repository: UserDataRepository) : FireBaseViewModel() {
    private val TAG: String = OwnerProfileViewModel::class.java.simpleName
    private val uuid: String = getUserUUID()
    private val storage: FirebaseStorage = FirebaseStorage.getInstance()
    private var _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User>
        get() = _user
    private var _updateSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val updateSuccess: LiveData<Boolean>
        get() = _updateSuccess
    private var _initSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val initSuccess: LiveData<Boolean>
        get() = _initSuccess
    var imageUri: Uri? = null

    fun updateUserProfile() {
        repository.getUserByUuid(uuid).addOnSuccessListener {
            val map = it.data as Map<String, Object>
            val user = map.toUser()
            _user.postValue(user)
        }
    }

    fun changeUserProfile(
        name: String,
        phone: String,
        password: String
    ) {
        if (name.isNotEmpty() && phone.isNotEmpty()
            && password.isNotEmpty() && password.length >= 6
        ) {
            val user = _user.value
            val email = user!!.email
            val password = user!!.password
            val credential = EmailAuthProvider.getCredential(email, password)
            if (imageUri == null) {
                user!!.also {
                    it.name = name
                    it.phone = phone
                    it.password = password
                }

                FirebaseAuth.getInstance().currentUser!!.reauthenticate(credential)
                    .addOnCompleteListener {
                        repository.updateUser(user).addOnCompleteListener {
                            updateEnd()
                        }
                    }
            } else {
                user!!.also {
                    it.name = name
                    it.phone = phone
                    it.password = password
                }
                FirebaseAuth.getInstance().currentUser!!.reauthenticate(credential)
                    .addOnCompleteListener {
                        updateImageDatabase(imageUri!!, user)
                    }
            }
        }
    }

    private fun updateImageDatabase(uri: Uri, user: User) {
        uri.apply {
            val fileName = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
            storage.reference.child("image").child(fileName)
                .putFile(uri)
                .addOnSuccessListener { task ->
                    task.metadata?.reference?.downloadUrl?.addOnSuccessListener {
                        val url = it.toString()
                        user.image = url
                        repository.updateUser(user).addOnCompleteListener {
                            updateEnd()
                        }
                    }
                }
        }
    }

    private fun updateEnd() {
        _updateSuccess.postValue(true)
    }

    fun initEnd() {
        _initSuccess.postValue(true)
    }


}