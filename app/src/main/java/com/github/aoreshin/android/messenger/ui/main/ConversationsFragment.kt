package com.github.aoreshin.android.messenger.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.aoreshin.android.messenger.R
import com.github.aoreshin.android.messenger.ui.chat.ChatActivity
import com.github.aoreshin.android.messenger.ui.chat.ChatView
import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences
import com.github.aoreshin.android.messenger.ui.data.vo.ConversationVO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ConversationsFragment : Fragment(), View.OnClickListener {
    private lateinit var activity: MainActivity
    private lateinit var rvConversations: RecyclerView
    private lateinit var fabContacts: FloatingActionButton
    var conversations: ArrayList<ConversationVO> = ArrayList()
    lateinit var conversationsAdapter: ConversationsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val baseLayout = inflater.inflate(R.layout.fragment_conversations, container, false)
        rvConversations = baseLayout.findViewById(R.id.rv_conversations)
        fabContacts = baseLayout.findViewById(R.id.fab_contacts)
        conversationsAdapter = ConversationsAdapter(getActivity()!!.baseContext, conversations)

        rvConversations.adapter = conversationsAdapter
        rvConversations.layoutManager = LinearLayoutManager(getActivity()?.baseContext)
        fabContacts.setOnClickListener(this)
        return baseLayout
    }

    override fun onClick(view: View) {
        if (view.id == R.id.fab_contacts) {
            this.activity.showContactsScreen()
        }
    }

    fun setActivity(activity: MainActivity) {
        this.activity = activity
    }

    class ConversationsAdapter(private val context: Context, private val dataSet: List<ConversationVO>) :
        RecyclerView.Adapter<ConversationsAdapter.ViewHolder>(),
        ChatView.ChatAdapter {
        val preferences: AppPreferences = AppPreferences.create(context)

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = dataSet[position] // get item at current position
            val itemLayout = holder.itemLayout // bind view holder layout // to local variable
            itemLayout.findViewById<TextView>(R.id.tv_username).text = item.secondPartyUsername
            itemLayout.findViewById<TextView>(R.id.tv_preview).text = item.messages[item.messages.size - 1].body
            itemLayout.setOnClickListener {
                val message = item.messages[0]
                val recipientId: Long
                recipientId = if (message.senderId ==
                    preferences.userDetails.id) {
                    message.recipientId
                } else {
                    message.senderId
                }
                navigateToChat(item.secondPartyUsername,
                    recipientId, item.conversationId)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): ViewHolder {
            val itemLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.vh_conversations, null, false)
                .findViewById<LinearLayout>(R.id.ll_container)
            return ViewHolder(itemLayout)
        }

        override fun getItemCount(): Int {
            return dataSet.size
        }

        override fun navigateToChat(recipientName: String,
                                    recipientId: Long, conversationId: Long?) {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("CONVERSATION_ID", conversationId)
            intent.putExtra("RECIPIENT_ID", recipientId)
            intent.putExtra("RECIPIENT_NAME", recipientName)
            context.startActivity(intent)
        }

        class ViewHolder(val itemLayout: LinearLayout) : RecyclerView.ViewHolder(itemLayout)
    }
}