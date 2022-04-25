package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.User
import com.example.myapplication.data.repository.LocalDataDao

@Database(entities = [User::class, Friend::class], version = 7)
abstract class LocalRoomDB : RoomDatabase() {

    abstract fun localDataDao(): LocalDataDao

    companion object {
        private var INSTANCE: LocalRoomDB? = null

        fun getInstance(context: Context): LocalRoomDB? {
            if (INSTANCE == null) {
                synchronized(LocalRoomDB::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LocalRoomDB::class.java,
                        "local_room.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}