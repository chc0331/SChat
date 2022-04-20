package com.example.myapplication.toyproject.presenter.chatting.messenger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.toyproject.R
import com.example.myapplication.toyproject.databinding.FragmentMessengerBinding

class MessengerFragment : Fragment() {

    private lateinit var binding: FragmentMessengerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_messenger, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rootActivity = activity as AppCompatActivity
        val navController = findNavController()
        rootActivity?.setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(rootActivity, navController)
        binding.toolbar.apply {
            setNavigationOnClickListener { navController.navigateUp() }
            title = ""
        }
    }

    override fun onResume() {
        super.onResume()
        setSoftKeyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun onStop() {
        super.onStop()
        setSoftKeyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
    }

    private fun setSoftKeyboardMode(mode: Int) {
        activity?.window?.setSoftInputMode(mode)
    }
}