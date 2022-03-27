package com.example.myapplication.toyproject.presenter.login

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
import com.example.myapplication.toyproject.databinding.FragmentLoginBinding
import com.example.myapplication.toyproject.util.isLogged
import com.example.myapplication.toyproject.network.NetworkHandler
import com.example.myapplication.toyproject.presenter.viewmodel.ViewModelFactory
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelFactory(userRepository)
        ).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login,
            container, false
        )
        binding.loginFragment = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    override fun onStart() {
        super.onStart()
        if (Firebase.auth.isLogged()) {
            goToFriendListFragment()
        }
    }

    private fun observeViewModel() {
        viewModel.loginSuccess.observe(viewLifecycleOwner, {
            if (it)
                goToFriendListFragment()
            Toast.makeText(context, if (it) "로그인 성공" else "로그인 실패", Toast.LENGTH_SHORT).show()
        })
    }

    fun loginButtonClicked() {
        if (!NetworkHandler.isNetworkAvailable(context)) {
            Toast.makeText(context, "네트워크 연결이 안되어 있습니다.", Toast.LENGTH_SHORT).show()
        } else {
            viewModel.login(
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString()
            )
        }
    }

    fun goToFindIdFragment() {
        val action = LoginFragmentDirections.actionGoToFindId()
        Navigation.findNavController(binding.findIdText).navigate(action)
    }

    fun goToSignUpFragment() {
        val action = LoginFragmentDirections.actionGoToSignUp()
        Navigation.findNavController(binding.signUpText).navigate(action)
    }

    private fun goToFriendListFragment() {
        val action = LoginFragmentDirections.actionGoToFriendList()
        Navigation.findNavController(binding.loginButton).navigate(action)
    }
}