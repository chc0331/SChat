package com.example.myapplication.toyproject.login.presenter.chatting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.toyproject.R
import com.example.myapplication.toyproject.databinding.FragmentFriendListBinding
import com.example.myapplication.toyproject.login.core.BaseFragment
import com.example.myapplication.toyproject.login.presenter.chatting.adapter.FriendListAdapter
import com.example.myapplication.toyproject.login.presenter.viewmodel.ViewModelFactory
import com.example.myapplication.toyproject.login.util.getCurrentUserName
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FriendListFragment : BaseFragment() {

    private val friendListAdapter = FriendListAdapter()
    private lateinit var binding: FragmentFriendListBinding

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
            viewModel =
                ViewModelProvider(this@FriendListFragment, ViewModelFactory(userRepository)).get(
                    FriendListViewModel::class.java
                )
            userName.text = Firebase.auth.getCurrentUserName()
            toolbar.inflateMenu(R.menu.friend_list_fragment_menu)
            toolbar.setOnMenuItemClickListener { item ->
                optionClicked(item)
            }
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = friendListAdapter
        }
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        binding.viewModel?.getFriends()
    }

    private fun observeViewModel() {
        binding.viewModel?.friendList?.observe(viewLifecycleOwner, { it ->
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