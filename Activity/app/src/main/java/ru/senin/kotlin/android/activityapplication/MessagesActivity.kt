package ru.senin.kotlin.android.activityapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_messages.*
import kotlinx.android.synthetic.main.message_item.view.*
import kotlinx.coroutines.runBlocking
import ru.senin.kotlin.android.activityapplication.db.*

class MessagesActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder>
    private lateinit var model: MessagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        // DEBUG DATA
        insertTestMessages()

        messagesRecyclerView.layoutManager = LinearLayoutManager(this@MessagesActivity)

        adapter = MessagesAdapter()
        messagesRecyclerView.adapter = adapter

        model = ViewModelProvider(this).get(MessagesViewModel::class.java)
        model.messages.observe(this,
            Observer { messages ->
                (adapter as MessagesAdapter).setMessages(messages)
            })
    }

    private fun insertTestMessages() {
        val database = MessagesDatabase(this)
        runBlocking {
            val dao = database.dao()
            dao.deleteAllMessages()
            dao.deleteAllUsers()
            dao.insertAllUsers(
                User("pupkin", "Василий Пупкин"),
                User("sidorov", "Семён Сидоров"),
                User("ivanov", "Иван Иванов")
            )
            dao.insertAllMessages(
                Message(
                    "pupkin",
                    "sidorov",
                    "Hello!",
                    1585245920
                ),
                Message(
                    "sidorov",
                    "pupkin",
                    "Hi!",
                    1585246920
                )
            )
        }
    }
}

class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder>() {
    class MessagesViewHolder(val itemLayout: View) : RecyclerView.ViewHolder(itemLayout)

    // Cached messages
    private val messagesCache = mutableListOf<Message>()

    fun setMessages(messages: List<Message>) {
        messagesCache.clear()
        messagesCache.addAll(messages)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return MessagesViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        val message = messagesCache[position]
        val fromUser = message.from
        val toUser = message.to
        holder.itemLayout.messageHeaderTextView.text = "$fromUser ==> $toUser"
        holder.itemLayout.bodyTextView.text = message.body
    }

    override fun getItemCount() = messagesCache.size
}