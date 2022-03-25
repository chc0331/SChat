package com.example.myapplication.toyproject.login.core

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.data.repositoryimpl.LocalDataRepositoryImpl
import com.example.myapplication.data.repositoryimpl.RemoteDataRepositoryImpl
import com.example.myapplication.data.repositoryimpl.UserDataRepositoryImpl

open class BaseFragment : Fragment() {
    lateinit var userRepository: UserDataRepository

    override fun onCreate(savedInstanceState: Bundle?) { //
        super.onCreate(savedInstanceState)
        userRepository = UserDataRepositoryImpl(
            LocalDataRepositoryImpl(context?.applicationContext),
            RemoteDataRepositoryImpl()
        )
    }
}
