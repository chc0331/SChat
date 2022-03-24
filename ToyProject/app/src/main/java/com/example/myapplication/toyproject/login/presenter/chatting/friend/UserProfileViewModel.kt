package com.example.myapplication.toyproject.login.presenter.chatting.friend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.toyproject.login.core.FireBaseViewModel

class UserProfileViewModel(private val userRepository: UserDataRepository) : FireBaseViewModel() {
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

    fun setName(name: String) {
        _name.postValue(name)
    }

    fun changeName(friend: Friend, name: String) {
        _name.postValue(name)
        friend.friendName = name
        userRepository.updateFriend(friend)
    }
}
