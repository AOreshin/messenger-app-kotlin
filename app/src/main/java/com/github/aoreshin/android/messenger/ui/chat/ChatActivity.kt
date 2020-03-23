package com.github.aoreshin.android.messenger.ui.chat

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.github.aoreshin.android.messenger.R
import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences
import com.github.aoreshin.android.messenger.utils.message.Message
import com.stfalcon.chatkit.messages.MessageInput
import com.stfalcon.chatkit.messages.MessagesList
import com.stfalcon.chatkit.messages.MessagesListAdapter
import java.util.*

class ChatActivity : AppCompatActivity(), ChatView,
    MessageInput.InputListener {

    private var recipientId: Long = -1
    private lateinit var messageList: MessagesList
    private lateinit var messageInput: MessageInput
    private lateinit var preferences: AppPreferences
    private lateinit var presenter: ChatPresenter
    private lateinit var messageListAdapter: MessagesListAdapter<Message>
    private var conversationId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("RECIPIENT_NAME")

        preferences = AppPreferences.create(this)

        messageListAdapter = MessagesListAdapter(preferences.userDetails.id.toString(), null)
        presenter = ChatPresenterImpl(this)
        bindViews()
        recipientId = intent.getLongExtra("RECIPIENT_ID", -1)

        conversationId = intent.getLongExtra("CONVERSATION_ID", -1)

        if (conversationId != -1L) {
            presenter.loadMessages(conversationId)
        }
    }

    override fun showConversationLoadError() {
        Toast.makeText(this, "Unable to load thread. Please try again later.", Toast.LENGTH_LONG).show()
    }

    override fun showMessageSendError() {
        Toast.makeText(this, "Unable to send message. Please try again later.", Toast.LENGTH_LONG).show()
    }

    override fun getContext(): Context {
        return this
    }

    override fun getMessageListAdapter(): MessagesListAdapter<Message> {
        return messageListAdapter
    }

    override fun bindViews() {
        messageList = findViewById(R.id.messages_list)
        messageInput = findViewById(R.id.message_input)

        messageList.setAdapter(messageListAdapter)
        messageInput.setInputListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onSubmit(input: CharSequence?): Boolean {
        messageListAdapter.addToStart(Message(preferences.userDetails.id, input.toString(), Date()), true)
        presenter.sendMessage(recipientId, input.toString())
        return true
    }

    fun onClickRefresh(view: View) {
        if (conversationId != -1L) {
            presenter.loadMessages(conversationId)
        }
    }
}
