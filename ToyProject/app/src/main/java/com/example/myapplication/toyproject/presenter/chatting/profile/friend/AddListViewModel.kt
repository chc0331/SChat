package com.example.myapplication.toyproject.presenter.chatting.profile.friend

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
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document["email"]!! == email) {
                        val friend = Friend(
                            getUserUUID(),
                            document["uuid"] as String, document["email"] as String,
                            document["name"] as String, document["phone"] as String
                        )
                        repository.addFriend(friend).subscribe {
                            _success.postValue(true)
                        }
                        break
                    }
                }
            }.addOnFailureListener {
                _success.postValue(false)
            }
    }
}