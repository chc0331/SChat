package com.example.myapplication.toyproject.presenter.chatting.messenger

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.Chat
import com.example.myapplication.toyproject.databinding.ChatListLayoutBinding

class MessengerListAdapter(private val list: List<Chat>) :
    RecyclerView.Adapter<MessengerListAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChatListLayoutBinding.inflate(inflater)
        return MessageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class MessageViewHolder(val binding: ChatListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Chat) {
            binding.content.text = chat.contents
            //todo : need to add time property
//            binding.time.text = chat.time
        }
    }
}