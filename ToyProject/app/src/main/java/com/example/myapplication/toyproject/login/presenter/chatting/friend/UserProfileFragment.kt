package com.example.myapplication.toyproject.login.presenter.chatting.friend

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.myapplication.data.model.ParcelableFriend
import com.example.myapplication.toyproject.R
import com.example.myapplication.toyproject.databinding.FragmentUserProfileBinding
import com.example.myapplication.toyproject.login.core.BaseFragment
import com.example.myapplication.toyproject.login.presenter.viewmodel.ViewModelFactory

class UserProfileFragment : BaseFragment() {

    private lateinit var friend: ParcelableFriend
    private lateinit var binding: FragmentUserProfileBinding
    private lateinit var viewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: UserProfileFragmentArgs by navArgs()
        viewModel =
            ViewModelProvider(activity!!, ViewModelFactory(userRepository)).get(
                UserProfileViewModel::class.java
            )
        friend = args.friend
        friend.friendName?.let { viewModel.setName(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_user_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        binding.editButton.setOnClickListener {
            goToEditNameFragment()
        }
    }

    private fun observeViewModel() {
        viewModel.name.observe(viewLifecycleOwner, {
            Log.d("heec.choi", "observeViewModel $it")
            binding.userName.text = it
        })
    }

    private fun goToEditNameFragment() {
        val action =
            UserProfileFragmentDirections.actionGoToEditName(friend)
        Navigation.findNavController(binding.editButton).navigate(action)
    }
}