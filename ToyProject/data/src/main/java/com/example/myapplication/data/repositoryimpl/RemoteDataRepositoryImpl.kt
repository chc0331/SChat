package com.example.myapplication.data.repositoryimpl

import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.RemoteDataRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class RemoteDataRepositoryImpl : RemoteDataRepository {

    override var fireStore: FirebaseFirestore
        get() = FirebaseFirestore.getInstance()
        set(value) {}

    override fun getAllUsers(): Task<QuerySnapshot> {
        return fireStore.collection("Users")
            .get()
    }

    override fun getAllFriends(userId: String): Task<QuerySnapshot> {
        return fireStore.collection("Friends")
            .document(userId)
            .collection("friends")
            .get()
    }

    override fun addFriend(friend: Friend): Task<Void> {
        return fireStore.collection("Friends").document(friend.ownerUuid)
            .collection("friends").document(friend.friendUuid)
            .set(friend.toMap())
    }

    override fun getFriend(uuid: String): Task<QuerySnapshot> {
        return fireStore.collection("Friends")
            .document(uuid)
            .collection("friends")
            .get()
    }

    override fun addUser(user: User): Task<Void> {
        return fireStore.collection("Users")
            .document(user.uuid).set(user)
    }

    override fun getUserByUuid(uuid: String): Task<DocumentSnapshot> {
        return fireStore.collection("Users")
            .document(uuid).get()
    }

    override fun getUserByEmail(email: String): Task<QuerySnapshot> {
        return fireStore.collection("Users")
            .whereEqualTo("email", email)
            .get()
    }

    override fun updateUser(user: User): Task<Void> {
        return fireStore.collection("Users").document(user.uuid).set(user)
    }

    override fun updateFriend(friend: Friend): Task<Void> {
        return fireStore.collection("Friends")
            .document(friend.ownerUuid)
            .collection("friends")
            .document(friend.friendUuid)
            .set(friend.toMap())
    }
}