package com.example.myapplication.toyproject.presenter.chatting.messenger

import android.util.Log
import com.example.myapplication.toyproject.core.FireBaseViewModel
import com.google.firebase.firestore.FirebaseFirestore

class MessengerViewModel : FireBaseViewModel() {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun sendText(text: String, receiver: String) {
        val sender = getUserUUID()
        val document = db.collection("Chats").document()
        val messageId = document.id
        val messageHashMap = hashMapOf(
            "sender" to sender,
            "receiver" to receiver,
            "contents" to text,
            "url" to "",
            "messageId" to messageId
        )

        document.set(messageHashMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("heec.choi", "successful")
                }
            };
    }

}