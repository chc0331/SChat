package com.example.myapplication.toyproject.presenter.login.signup

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
import com.example.myapplication.toyproject.databinding.FragmentSignUpBinding
import com.example.myapplication.toyproject.network.NetworkHandler
import com.example.myapplication.toyproject.presenter.viewmodel.ViewModelFactory

class SignUpFragment : BaseFragment() {

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            signUpFragment = this@SignUpFragment
            lifecycleOwner = this@SignUpFragment
            vm = ViewModelProvider(
                this@SignUpFragment,
                ViewModelFactory(userRepository)
            ).get(SignUpViewModel::class.java)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        binding.vm?.apply {
            registerToast?.observe(viewLifecycleOwner, {
                Toast.makeText(
                    context, it,
                    Toast.LENGTH_SHORT
                ).show()
            })
            movePage?.observe(viewLifecycleOwner, {
                if (it)
                    Navigation.findNavController(binding.registerButton).popBackStack()
            })
        }
    }

    fun registerClicked() {
        if (!NetworkHandler.isNetworkAvailable(context)) {
            Toast.makeText(context, "네트워크 연결이 안되어 있습니다.", Toast.LENGTH_SHORT).show()
        } else {
            binding.vm?.createAccount()
        }
    }
}