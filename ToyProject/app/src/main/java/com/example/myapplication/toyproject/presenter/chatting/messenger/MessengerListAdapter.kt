package com.example.myapplication.toyproject.presenter.chatting.messenger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.Chat
import com.example.myapplication.toyproject.databinding.ChatListLayoutBinding
import com.google.firebase.auth.FirebaseAuth

class MessengerListAdapter(private val friendId: String) :
    RecyclerView.Adapter<MessengerListAdapter.MessageViewHolder>() {
    private var list: List<Chat> = listOf()
    private val userId = FirebaseAuth.getInstance().currentUser!!.uid

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
            if (chat.sender == userId && chat.receiver == friendId) {
                binding.apply {
                    friendChat.visibility = View.GONE
                    userChat.visibility = View.VISIBLE
                    userContent.text = chat.contents
                }
            } else if (chat.sender == friendId && chat.receiver == userId) {
                binding.apply {
                    userChat.visibility = View.GONE
                    friendChat.visibility = View.VISIBLE
                    friendContent.text = chat.contents
                }
            }
            //todo : need to add time property
//            binding.time.text = chat.time
        }
    }

    fun setList(messenger: List<Chat>) {
        this.list = messenger
        notifyDataSetChanged()
    }
}