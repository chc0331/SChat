package com.example.myapplication.toyproject.login.presenter.chatting.friend

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.myapplication.data.model.ParcelableFriend
import com.example.myapplication.toyproject.R
import com.example.myapplication.toyproject.databinding.FragmentEditNameBinding
import com.example.myapplication.toyproject.login.core.BaseFragment
import com.example.myapplication.toyproject.login.presenter.viewmodel.ViewModelFactory

class EditNameFragment : BaseFragment() {

    private lateinit var friend: ParcelableFriend
    private lateinit var binding: FragmentEditNameBinding
    private lateinit var viewModel: UserProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: EditNameFragmentArgs by navArgs()
        friend = args.friend
        viewModel =
            ViewModelProvider(activity!!, ViewModelFactory(userRepository)).get(
                UserProfileViewModel::class.java
            )
    }

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
        binding.editNameFragment = this
        observeViewModel()
        binding.changeButton.setOnClickListener {
            viewModel.changeName(friend.makeFriend(), binding.editName.text.toString())
            Navigation.findNavController(it).popBackStack()
            Toast.makeText(context, "변경되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        viewModel.name.observe(viewLifecycleOwner, Observer {
            binding.editName.setText(it)
        })
    }

}