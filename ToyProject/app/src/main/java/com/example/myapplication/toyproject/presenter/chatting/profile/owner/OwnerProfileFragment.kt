package com.example.myapplication.toyproject.presenter.chatting.profile.owner

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
    private val dialog: Dialog by lazy {
        AlertDialog.Builder(context)
            .setView(R.layout.progress_dialog)
            .create()
    }
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                val uri = it.data!!.data
                binding?.profileImage.background = null
                Glide.with(activity!!).load(uri)
                    .override(200, 200)
                    .into(binding?.profileImage)
                vm?.imageUri = uri
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
        vm.user.observe(viewLifecycleOwner, {
            binding.apply {
                Glide.with(activity!!).load(it.image)
                    .placeholder(R.drawable.ic_profile_icon)
                    .override(200, 200)
                    .into(profileImage)
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
            if (vm!!.changeUserProfile(
                    binding.editProfileName.text.toString(),
                    binding.editProfilePhone.text.toString(),
                    binding.editProfilePassword.text.toString()
                )
            ) {
                vm.updateStart()
                Toast.makeText(context, "Profile update success", Toast.LENGTH_SHORT).show()
                goToSettingsFragment()
            } else
                Toast.makeText(context, "Profile update fail", Toast.LENGTH_SHORT).show()
        }
        binding.changeProfileCancel.setOnClickListener {
            goToSettingsFragment()
        }
        dialog?.apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
        }
        vm.updateUserProfile()
    }

    private fun goToSettingsFragment() {
        findNavController().popBackStack()
    }

    private fun showProgressDialog(on: Boolean) {
        if (on)
            dialog.show()
        else
            dialog.dismiss()
    }
}