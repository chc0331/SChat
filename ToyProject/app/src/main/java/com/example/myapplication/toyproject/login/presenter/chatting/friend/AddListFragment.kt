package com.example.myapplication.toyproject.login.presenter.chatting.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.myapplication.toyproject.R
import com.example.myapplication.toyproject.databinding.FragmentAddListBinding
import com.example.myapplication.toyproject.login.core.BaseFragment
import com.example.myapplication.toyproject.login.presenter.viewmodel.ViewModelFactory

class AddListFragment : BaseFragment() {

    private lateinit var binding: FragmentAddListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_list,
            container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = this@AddListFragment
            vm = ViewModelProvider(this@AddListFragment, ViewModelFactory(userRepository)).get(
                AddListViewModel::class.java
            )
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        binding.vm?.success?.observe(viewLifecycleOwner, {
            Toast.makeText(context, if (it) "추가 성공" else "추가 실패", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(binding.button).popBackStack()
        })
    }
}