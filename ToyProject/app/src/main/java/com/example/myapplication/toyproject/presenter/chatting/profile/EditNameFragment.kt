package com.example.myapplication.toyproject.presenter.chatting.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.myapplication.toyproject.R
import com.example.myapplication.toyproject.core.BaseFragment
import com.example.myapplication.toyproject.databinding.FragmentEditNameBinding
import com.example.myapplication.toyproject.presenter.chatting.profile.friend.FriendProfileViewModel
import com.example.myapplication.toyproject.presenter.viewmodel.ViewModelFactory

class EditNameFragment : BaseFragment() {

    private lateinit var binding: FragmentEditNameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_name, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@EditNameFragment
            fm = this@EditNameFragment
            vm = ViewModelProvider(activity!!, ViewModelFactory(userRepository)).get(
                FriendProfileViewModel::class.java
            )
        }
    }

    fun changeClicked(){
        binding.vm?.changeName(binding.editName.text.toString())
        Navigation.findNavController(binding.changeButton).popBackStack()
        Toast.makeText(context, "변경되었습니다.", Toast.LENGTH_SHORT).show()
    }

}