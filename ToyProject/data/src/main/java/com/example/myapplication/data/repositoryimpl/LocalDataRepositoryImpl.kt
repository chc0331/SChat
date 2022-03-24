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
import io.reactivex.rxjava3.schedulers.Schedulers

class LocalDataRepositoryImpl(context: Context?) : LocalDataRepository {

    private var localDao: LocalDataDao = context?.let {
        LocalRoomDB.getInstance(it)?.localDataDao()
    }!!


    override fun addFriend(friend: Friend) {
        localDao?.addFriend(friend)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe {
                Log.d("heec.choi", "(local)addFriends finished")
            }
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

    override fun getAllFriends(friends: MutableLiveData<List<Friend>>) {
        localDao?.getAllFriends().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                friends.postValue(it)
                Log.d("heec.choi", "(local)getAllFriends : $it")
            }
    }

    override fun addUser(user: User) {
        localDao?.addUser(user)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe {
                Log.d("heec.choi", "(local)addUser Complete")
            }
    }

    override fun getUser(email: String, user: MutableLiveData<User>) {
        localDao?.getUser(email)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe { it ->
                user.postValue(it)
                Log.d("heec.choi", "(local)getUser Complete")
            }
    }

    override fun getUsers() {
        localDao?.getUsers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("heec.choi", "(local)getUsers : $it")
            }
    }

    override fun deleteFriends(): Completable {
        return localDao?.deleteFriends().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun deleteUsers() {
        localDao?.deleteUsers().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("heec.choi", "(local)deleteUsers")
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