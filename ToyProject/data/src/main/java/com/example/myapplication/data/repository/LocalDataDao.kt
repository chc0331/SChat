package com.example.myapplication.data.repository

import androidx.room.*
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

/*
* Flowable : Back pressure 대응을 위한 기술.
* Back pressure : DB나 서버로부터 데이터들을 가져오는 속도에 비해, 처리되는 속도의 차이가 생겨서
* 발생하는 문제. 따라서 처리하는 곳의 일이 쌓여있다면 데이터를 계속 가져오기 보다는 일을 어느정도까지 처리
* 하도록 기다려주고 쌓인 일이 조금 해결되면 데이터를 가져와 일을 처리하도록함.
*
* Completable : 데이터를 발행할 필요없이 수행만 하고 종료시키기 위해서 사용.
* */
@Dao
interface LocalDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFriend(friend: Friend): Completable

    @Query("SELECT * FROM friends WHERE friend_email = :email")
    fun getFriend(email: String): Flowable<Friend>

    @Query("SELECT * FROM friends")
    fun getAllFriends(): Flowable<List<Friend>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User): Completable

    @Query("SELECT * FROM users WHERE email= :email")
    fun getUser(email: String): Flowable<User>

    @Query("SELECT * FROM users")
    fun getUsers(): Flowable<List<User>>

    @Query("DELETE FROM users")
    fun deleteUsers(): Completable

    @Query("DELETE FROM friends")
    fun deleteAllFriends(): Completable

    @Update
    fun updateFriend(friend: Friend): Completable
}