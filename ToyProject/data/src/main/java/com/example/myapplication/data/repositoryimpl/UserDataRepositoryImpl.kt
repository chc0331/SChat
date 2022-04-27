package com.example.myapplication.data.repositoryimpl

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.LocalDataRepository
import com.example.myapplication.data.repository.RemoteDataRepository
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.data.toFriend
import com.example.myapplication.data.toUser
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QueryDocumentSnapshot
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe

class UserDataRepositoryImpl(
    private val localDataRepository: LocalDataRepository,
    private val remoteDataRepository: RemoteDataRepository
) : UserDataRepository {

    override fun initFriendData(email: String) {
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

    override fun initUserData() {
        localDataRepository.deleteAllUsers()
            .doOnComplete {
                remoteDataRepository.getAllUsers()
                    .addOnSuccessListener {
                        for (document: DocumentSnapshot in it.documents) {
                            val user = (document.data as Map<String, Object>).toUser()
                            localDataRepository.addUser(user).subscribe()
                        }
                    }
            }.subscribe()
    }

    override fun addFriend(friend: Friend) = localDataRepository.addFriend(friend)

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

    override fun getUserByUuid(uuid: String): Task<DocumentSnapshot> =
        remoteDataRepository.getUserByUuid(uuid)

    override fun getUserByEmail(email: String): Maybe<User> =
        localDataRepository.getUserByEmail(email)


    override fun getAllUsers(): Flowable<List<User>> {
        return localDataRepository.getAllUsers()
    }

    override fun deleteAllUsers() {
        localDataRepository.deleteAllUsers()
    }

    override fun deleteAllFriends() {
        localDataRepository.deleteAllFriends()
    }

    override fun updateUser(user: User) {
        remoteDataRepository.updateUser(user)
        localDataRepository.updateUser(user)
    }

    override fun updateFriend(friend: Friend) {
        remoteDataRepository.updateFriend(friend)
        localDataRepository.updateFriend(friend)
    }
}