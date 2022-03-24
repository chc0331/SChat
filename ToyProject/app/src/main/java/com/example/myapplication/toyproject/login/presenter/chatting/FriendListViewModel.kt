package com.example.myapplication.toyproject.login.presenter.chatting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.data.set
import com.example.myapplication.toyproject.login.core.FireBaseViewModel
import java.util.*

class FriendListViewModel(private val repository: UserDataRepository) : FireBaseViewModel() {

    private val _friendList: MutableLiveData<List<Friend>> =
        MutableLiveData<List<Friend>>().set(Collections.emptyList())
    val friendList: LiveData<List<Friend>>
        get() = _friendList

    fun getFriends() {
//        repository.getUsers()
        repository.getAllFriends(_friendList)
    }
}
