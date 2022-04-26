package com.example.myapplication.toyproject.presenter.chatting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.data.repository.UserDataRepository
import com.example.myapplication.data.repositoryimpl.LocalDataRepositoryImpl
import com.example.myapplication.data.repositoryimpl.RemoteDataRepositoryImpl
import com.example.myapplication.data.repositoryimpl.UserDataRepositoryImpl
import com.example.myapplication.toyproject.R
import com.example.myapplication.toyproject.core.BaseFragment
import com.example.myapplication.toyproject.databinding.FragmentFriendListBinding
import com.example.myapplication.toyproject.presenter.chatting.adapter.FriendListAdapter
import com.example.myapplication.toyproject.presenter.viewmodel.ViewModelFactory
import com.example.myapplication.toyproject.util.getCurrentUserName
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FriendListFragment : BaseFragment() {

    private val friendListAdapter = FriendListAdapter()
    private lateinit var binding: FragmentFriendListBinding
    private val repository: UserDataRepository by lazy {
        UserDataRepositoryImpl(
            LocalDataRepositoryImpl(context),
            RemoteDataRepositoryImpl()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_friend_list,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@FriendListFragment
            vm =
                ViewModelProvider(this@FriendListFragment, ViewModelFactory(userRepository)).get(
                    FriendListViewModel::class.java
                )
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = friendListAdapter
            toolbar.inflateMenu(R.menu.friend_list_fragment_menu)
            toolbar.setOnMenuItemClickListener { item ->
                optionClicked(item)
            }
        }
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        binding.vm?.getFriends()
        binding.userName.text = Firebase.auth.getCurrentUserName()

        repository.getAllUsers().subscribe { it ->
            Log.d("heec.choi", it.toString())
        }

        //todo : need to know why select is not working
//        Log.d("heec.choi", Firebase.auth.uid + " ")
//        repository.getUserByUuid(Firebase.auth.uid!!)
//            .doOnComplete {
//                Log.d("heec.choi", "onComplete")
//            }
//            .doOnSuccess {
//                Log.d("heec.choi", "onSuccess")
//                Glide.with(activity!!).load(it.image)
//                    .into(binding.userProfileImage)
//            }.subscribe()

        repository.getUserByEmail("ss01003@naver.com")
            .doOnComplete {
                Log.d("heec.choi", "email onComplete")
            }.doOnSuccess {
                Log.d("heec.choi", "email : " + it.toString())
            }.subscribe()


    }

    private fun observeViewModel() {
        binding.vm?.friendList?.observe(viewLifecycleOwner, { it ->
            friendListAdapter.submitList(it)
        })
    }

    private fun optionClicked(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.addListFragment -> {
                goToAddListFragment(R.id.actionGotoAddListFragment)
            }
            R.id.setting -> {
                goToAddListFragment(R.id.actionGoToSettingFragment)
            }
        }
        return true
    }

    private fun goToAddListFragment(actionId: Int): Boolean {
        findNavController().navigate(actionId)
        return true
    }
}