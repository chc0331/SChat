package com.example.myapplication.toyproject.login.presenter.chatting.friend.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.ParcelableFriend
import com.example.myapplication.toyproject.databinding.FriendListItemBinding
import com.example.myapplication.toyproject.login.presenter.chatting.FriendListFragmentDirections

class FriendListAdapter(private val items: List<Friend>) :
    RecyclerView.Adapter<FriendListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FriendListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: FriendListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(friend: Friend) {
            with(binding) {
                this.friend = friend
                executePendingBindings() //바인딩을 즉시 실행
                binding.root.setOnClickListener {
                    goToUserProfileFragment(it, ParcelableFriend.friendToParcelable(friend))
                }
            }
        }
    }

    private fun goToUserProfileFragment(view: View, friend: ParcelableFriend) {
        val action = FriendListFragmentDirections.actionGoToUserProfile(friend)
        Navigation.findNavController(view).navigate(action)
    }
}