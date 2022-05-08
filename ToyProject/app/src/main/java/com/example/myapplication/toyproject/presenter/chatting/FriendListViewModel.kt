package com.example.myapplication.toyproject.presenter.chatting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.data.set
import com.example.myapplication.toyproject.core.FireBaseViewModel
import java.util.*
import kotlin.collections.ArrayList

class FriendListViewModel(private val repository: UserDataRepository) : FireBaseViewModel() {

    private val _friendList: MutableLiveData<List<Friend>> =
        MutableLiveData<List<Friend>>().set(Collections.emptyList())
    val friendList: LiveData<List<Friend>>
        get() = _friendList

    fun getFriends() {
        repository.getAllFriends(getUserUUID()).addOnSuccessListener { querySnapShot ->
            val list = ArrayList<Friend>()
            for (document in querySnapShot) {
                list.add(
                    Friend(
                        document["ownerUuid"].toString(),
                        document["friendUuid"].toString(),
                        document["friendEmail"].toString(),
                        document["friendName"].toString(),
                        document["friendPhone"].toString()
                    )
                )
            }
            _friendList.postValue(list)
        }
    }
}
