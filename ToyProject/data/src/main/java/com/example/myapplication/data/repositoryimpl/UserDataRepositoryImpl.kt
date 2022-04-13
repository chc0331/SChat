package com.example.myapplication.data.repositoryimpl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.LocalDataRepository
import com.example.myapplication.data.repository.RemoteDataRepository
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.data.toFriend
import com.google.firebase.firestore.QueryDocumentSnapshot
import io.reactivex.rxjava3.core.Flowable

class UserDataRepositoryImpl(
    private val localDataRepository: LocalDataRepository,
    private val remoteDataRepository: RemoteDataRepository
) : UserDataRepository {

    override fun initData(email: String) {
        //init friend data
        localDataRepository.deleteAllFriends()
            .doOnComplete {
                remoteDataRepository.getFriend(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            for (document: QueryDocumentSnapshot in task.result!!) {
                                val friend = (document.data as Map<String, Object>).toFriend()
                                localDataRepository.addFriend(friend)
                            }
                        }
                    }
            }.subscribe()
    }

    override fun addFriend(friend: Friend, result: MutableLiveData<Boolean>) {
        remoteDataRepository.getUser(friend.friendEmail).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (task.result?.isEmpty == false) {
                    friend.friendName = task.result!!.documents[0]["name"] as String
                    friend.friendPhone = task.result!!.documents[0]["phone"] as String
                    localDataRepository.addFriend(friend)
                    remoteDataRepository.addFriend(friend)
                    result.postValue(true)
                }
            }
        }
    }

    override fun getFriend(email: String, result: MutableLiveData<Friend>) =
        localDataRepository.getFriend(email, result)

    override fun getAllFriends(): Flowable<List<Friend>> {
        return localDataRepository.getAllFriends()
    }

    override fun addUser(user: User) {
        remoteDataRepository.addUser(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                localDataRepository.addUser(user)
            }
        }
    }

    override fun getUser(email: String, result: MutableLiveData<User>) =
        localDataRepository.getUser(email, result)

    override fun getUsers() {
        localDataRepository.getUsers()
    }

    override fun deleteUsers() {
        localDataRepository.deleteUsers()
    }

    override fun deleteAllFriends() {
        localDataRepository.deleteAllFriends()
    }

    override fun updateFriend(friend: Friend) {
        remoteDataRepository.updateFriend(friend)
        localDataRepository.updateFriend(friend)
    }
}