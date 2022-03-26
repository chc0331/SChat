package com.example.myapplication.toyproject.login.presenter.chatting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.Friend
import com.example.myapplication.data.model.ParcelableFriend
import com.example.myapplication.toyproject.databinding.FriendListItemBinding
import com.example.myapplication.toyproject.login.presenter.chatting.FriendListFragmentDirections


//ListAdapter에서 사용할수 있는 주요 Method
//getItem(position:Int):어댑터 내 List indexing을 할 때 사용
//getCurrentList(): 어댑터가 가지고 있는 리스트를 가져올 때 사용.
//submitList(MutableList<T> list) : 리스트 항목을 변경하고 싶을 때 사용한다.
class FriendListAdapter :
    ListAdapter<Friend, FriendListAdapter.FriendViewHolder>(FriendDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FriendListItemBinding.inflate(inflater, parent, false)
        return FriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FriendViewHolder(private val binding: FriendListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(friend: Friend) {
            with(binding) {
                this.friend = friend
                this.root.setOnClickListener {
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