package com.example.myapplication.toyproject.presenter.chatting.friend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.toyproject.core.FireBaseViewModel
import com.example.myapplication.toyproject.util.set

class UserProfileViewModel(private val userRepository: UserDataRepository) : FireBaseViewModel() {
    private val _friend = MutableLiveData<Friend?>().set(null)
    val friend: LiveData<Friend?>
        get() = _friend

    fun setFriend(friend: Friend?) {
        _friend.postValue(friend)
    }

    fun changeName(name: String) {
        _friend.value?.friendName = name
        _friend.value?.let {
            userRepository.updateFriend(it)
        }
    }
}
