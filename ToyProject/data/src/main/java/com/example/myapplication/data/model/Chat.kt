package com.example.myapplication.data.model

data class Chat(
    val contents: String,
    val messageId: String,
    val receiver: String,
    val sender: String,
    val url: String,
    val chatTime:String,
)
