package com.example.myapplication.toyproject.presenter.chatting.profile.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.myapplication.data.model.ParcelableFriend
import com.example.myapplication.toyproject.R
import com.example.myapplication.toyproject.core.BaseFragment
import com.example.myapplication.toyproject.databinding.FragmentUserProfileBinding
import com.example.myapplication.toyproject.presenter.viewmodel.ViewModelFactory

class FriendProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentUserProfileBinding
    private val friend: ParcelableFriend? by lazy {
        arguments?.let {
            val args = FriendProfileFragmentArgs.fromBundle(it)
            args.friend
        }
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
        binding.apply {
            lifecycleOwner = this@FriendProfileFragment
            fm = this@FriendProfileFragment
            vm = ViewModelProvider(activity!!, ViewModelFactory(userRepository)).get(
                FriendProfileViewModel::class.java
            )
        }
    }

    override fun onResume() {
        super.onResume()
        when (binding.vm?.friend?.value) {
            null -> binding.vm?.setFriend(friend?.makeFriend())
        }
    }

    fun goToEditNameFragment() {
        val action = FriendProfileFragmentDirections.actionGoToEditName()
        Navigation.findNavController(binding.editButton).navigate(action)
    }
}