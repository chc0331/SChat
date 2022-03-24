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
import com.example.myapplication.toyproject.login.presenter.chatting.friend.adapter.FriendListAdapter
import com.example.myapplication.toyproject.login.presenter.viewmodel.ViewModelFactory

class FriendListFragment : BaseFragment() {

    private lateinit var binding: FragmentFriendListBinding
    private lateinit var adapter: FriendListAdapter
    private lateinit var viewModel: FriendListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, ViewModelFactory(userRepository)).get(
                FriendListViewModel::class.java
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
            viewModel =
                ViewModelProvider(this@FriendListFragment, ViewModelFactory(userRepository)).get(
                    FriendListViewModel::class.java
                )
            userName.text = viewModel.getUserName()
            toolbar.inflateMenu(R.menu.friend_list_fragment_menu)
            toolbar.setOnMenuItemClickListener { item ->
                optionClicked(item)
            }
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFriends()
    }

    private fun observeViewModel() {
        binding.viewModel?.friendList?.observe(viewLifecycleOwner, {
            adapter = FriendListAdapter(it)
            binding.recyclerView.adapter = adapter
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