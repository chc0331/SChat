package com.example.myapplication.toyproject.presenter.chatting.profile.owner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.toyproject.R
import com.example.myapplication.toyproject.core.BaseFragment
import com.example.myapplication.toyproject.databinding.FragmentOwnerProfileBinding
import com.example.myapplication.toyproject.presenter.viewmodel.ViewModelFactory

class OwnerProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentOwnerProfileBinding
    private val vm: OwnerProfileViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(userRepository)).get(
            OwnerProfileViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_owner_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.user.observe(viewLifecycleOwner, { it ->
            binding.apply {
                editProfileName.setText(it.name)
                editProfilePhone.setText(it.phone)
                editProfilePassword.setText(it.password)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        vm.updateUserProfile()
    }
}