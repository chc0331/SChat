package com.example.myapplication.toyproject.login.presenter.chatting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
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
        binding.viewModel = viewModel
        binding.userName.text = viewModel.getUserName()
        binding.toolbar.inflateMenu(R.menu.friend_list_fragment_menu)
        binding.toolbar.setOnMenuItemClickListener { item ->
            optionClicked(item)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
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
//        val navController = findNavController()
//        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
        findNavController().navigate(actionId)
        return true
    }
}