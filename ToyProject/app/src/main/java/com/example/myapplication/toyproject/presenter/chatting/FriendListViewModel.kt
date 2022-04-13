package com.example.myapplication.toyproject.presenter.chatting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.data.set
import com.example.myapplication.toyproject.core.FireBaseViewModel
import com.example.myapplication.toyproject.util.getCurrentUserName
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.*

class FriendListViewModel(private val repository: UserDataRepository) : FireBaseViewModel() {

    private val _friendList: MutableLiveData<List<Friend>> =
        MutableLiveData<List<Friend>>().set(Collections.emptyList())
    val friendList: LiveData<List<Friend>>
        get() = _friendList

    fun getFriends() {
        repository.getAllFriends().subscribe {
            _friendList.postValue(it)
        }
    }
}
