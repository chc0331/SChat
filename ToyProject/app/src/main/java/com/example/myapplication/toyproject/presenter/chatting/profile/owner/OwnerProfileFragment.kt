package com.example.myapplication.toyproject.presenter.chatting.profile.owner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
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
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data!!.data
                binding?.profileImage.background = null
                Glide.with(activity!!).load(uri)
                    .override(200, 200)
                    .into(binding?.profileImage)
                vm?.imageUri = uri.toString()
            }
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
        binding.profileImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startForResult.launch(intent)
        }
        binding.changeProfileOk.setOnClickListener {
            vm?.changeUserProfile(
                binding.editProfileName.text.toString(),
                binding.editProfilePhone.text.toString(),
                binding.editProfilePassword.text.toString()
            )
        }
    }

    override fun onResume() {
        super.onResume()
        vm.updateUserProfile()
    }
}