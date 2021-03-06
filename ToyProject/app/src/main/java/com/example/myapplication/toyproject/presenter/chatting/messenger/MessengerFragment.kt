package com.example.myapplication.toyproject.presenter.chatting.messenger

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.toyproject.R
import com.example.myapplication.toyproject.databinding.FragmentMessengerBinding
import com.example.myapplication.toyproject.util.VerticalSpaceItemDecoration

class MessengerFragment : Fragment() {

    private lateinit var binding: FragmentMessengerBinding
    private val vm: MessengerViewModel by viewModels()
    private val friendUuid: String by lazy {
        val args: MessengerFragmentArgs by navArgs()
        args.friendArgs
    }

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
        val adapter = MessengerListAdapter(friendUuid)
        rootActivity?.setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(rootActivity, navController)
        binding.toolbar.apply {
            setNavigationOnClickListener { navController.navigateUp() }
            title = ""
        }
        binding.sendText.setOnClickListener {
            val text = binding.chatEditText.text.toString()
            binding.chatEditText.setText("")
            vm.sendText(text, friendUuid)
        }
        binding.chatList.layoutManager = LinearLayoutManager(context)
        binding.chatList.addItemDecoration(VerticalSpaceItemDecoration(20))
        binding.chatList.adapter = adapter
        vm.list.observe(viewLifecycleOwner) {
            adapter.setList(it)
            binding.chatList.scrollToPosition(it.size - 1)
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getChatsList(friendUuid)
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