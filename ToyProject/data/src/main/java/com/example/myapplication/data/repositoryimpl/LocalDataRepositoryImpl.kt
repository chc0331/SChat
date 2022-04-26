package com.example.myapplication.data.repositoryimpl

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.LocalRoomDB
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.LocalDataDao
import com.example.myapplication.data.repository.LocalDataRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.schedulers.Schedulers

class LocalDataRepositoryImpl(context: Context?) : LocalDataRepository {

    private var localDao: LocalDataDao = context?.let {
        LocalRoomDB.getInstance(it)?.localDataDao()
    }!!


    override fun addFriend(friend: Friend): Completable {
        return localDao?.addFriend(friend)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getFriend(email: String, friends: MutableLiveData<Friend>) {
        localDao?.getFriend(email)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { it ->
                friends.postValue(it)
                Log.d("heec.choi", "(local)getFriends finished")
            }
    }

    override fun getAllFriends(): Flowable<List<Friend>> {
        return localDao?.getAllFriends().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun addUser(user: User): Completable =
        localDao?.addUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getUserByUuid(uuid: String): Maybe<User> =
        localDao?.getUserByUuid(uuid).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getUserByEmail(email: String): Maybe<User> =
        localDao?.getUserByEmail(email).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun getAllUsers(): Flowable<List<User>> =
        localDao?.getAllUsers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())


    override fun deleteAllFriends(): Completable {
        return localDao?.deleteAllFriends().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteAllUsers(): Completable =
        localDao?.deleteUsers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun updateUser(user: User) {
        localDao?.updateUser(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("heec.choi", "(local)updateUser")
            }
    }


    override fun updateFriend(friend: Friend) {
        localDao?.updateFriend(friend).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("heec.choi", "(local)updateFriend")
            }
    }
}