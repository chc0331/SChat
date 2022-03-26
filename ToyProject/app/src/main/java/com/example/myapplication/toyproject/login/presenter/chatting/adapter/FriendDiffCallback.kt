package com.example.myapplication.toyproject.login.presenter.chatting.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.myapplication.data.model.Friend

object FriendDiffCallback : DiffUtil.ItemCallback<Friend>() {

    //이전 어댑터와 바뀌는 어댑터의 아이템이 동일한지 확인.
    override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem.friendEmail == newItem.friendEmail
    }

    //이전 어댑터와 바뀌는 어댑터의 아이템 내 내용을 비교.
    //areItemsTheSame에서 true가 나올경우 추가적으로 비교.
    override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem == newItem
    }

}