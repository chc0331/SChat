package com.example.myapplication.toyproject.presenter.chatting.messenger

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.Chat
import com.example.myapplication.toyproject.core.FireBaseViewModel
import com.example.myapplication.toyproject.util.set
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class MessengerViewModel : FireBaseViewModel() {

    private val sender = getUserUUID()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val _list = MutableLiveData<ArrayList<Chat>>().set(ArrayList())
    val list: LiveData<ArrayList<Chat>>
        get() = _list

    fun getChatsList(receiver: String) {
        val chatList = ArrayList<Chat>()
        db.collection("Chats")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if (!checkChat(document))
                        continue
                    val chat = Chat(
                        document["contents"].toString(),
                        document["messageId"].toString(),
                        document["receiver"].toString(),
                        document["sender"].toString(),
                        document["url"].toString()
                    )
                    chatList.add(chat)
                }
                _list.postValue(chatList)
            }
    }

    fun sendText(text: String, receiver: String) {
        val document = db.collection("Chats").document()
        val messageId = document.id
        val messageHashMap = hashMapOf(
            "sender" to sender,
            "receiver" to receiver,
            "contents" to text,
            "url" to "",
            "messageId" to messageId
        )
        val chat = Chat(text, messageId, receiver, sender, "")

        document.set(messageHashMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val chatList = db.collection("ChatList")
                    chatList.document(sender)
                        .set(
                            hashMapOf(
                                "target" to receiver
                            )
                        )
                    chatList.document(receiver)
                        .set(
                            hashMapOf(
                                "target" to sender
                            )
                        )
                    _list.apply {
                        value!!.add(chat)
                        postValue(value)
                    }
                }
            }
    }

    private fun checkChat(document: QueryDocumentSnapshot): Boolean {
        val docuSender = document["sender"]
        val docuReceiver = document["receiver"]
        if (sender == docuSender || sender == docuReceiver) return true
        return false
    }

}