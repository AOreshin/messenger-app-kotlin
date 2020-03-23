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
import com.github.aoreshin.android.messenger.ui.data.vo.UserVO

class ContactsFragment : Fragment() {
    private lateinit var activity: MainActivity
    private lateinit var rvContacts: RecyclerView
    var contacts: ArrayList<UserVO> = ArrayList()
    lateinit var contactsAdapter: ContactsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val baseLayout = inflater.inflate(R.layout.fragment_contacts, container, false)
        rvContacts = baseLayout.findViewById(R.id.rv_contacts)
        contactsAdapter = ContactsAdapter(getActivity()!!.baseContext, contacts)
        rvContacts.adapter = contactsAdapter
        rvContacts.layoutManager = LinearLayoutManager(getActivity()!!.baseContext)
        return baseLayout
    }

    fun setActivity(activity: MainActivity) {
        this.activity = activity
    }

    class ContactsAdapter(private val context: Context, private val dataSet: List<UserVO>) :
        RecyclerView.Adapter<ContactsAdapter.ViewHolder>(),
        ChatView.ChatAdapter {
        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): ViewHolder {
            val itemLayout = LayoutInflater.from(parent.context).inflate(R.layout.vh_contacts, parent, false)
            val llContainer = itemLayout.findViewById<LinearLayout>(R.id.ll_container)
            return ViewHolder(llContainer)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = dataSet[position]
            val itemLayout = holder.itemLayout
            itemLayout.findViewById<TextView>(R.id.tv_username).text = item.username
            itemLayout.findViewById<TextView>(R.id.tv_phone).text = item.phoneNumber
            itemLayout.findViewById<TextView>(R.id.tv_status).text = item.status
            itemLayout.setOnClickListener { navigateToChat(item.username!!, item.id) }
        }

        override fun getItemCount(): Int {
            return dataSet.size
        }

        override fun navigateToChat(recipientName: String,
                                    recipientId: Long, conversationId: Long?) {
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("RECIPIENT_ID", recipientId)
            intent.putExtra("RECIPIENT_NAME", recipientName)
            context.startActivity(intent)
        }

        class ViewHolder(val itemLayout: LinearLayout) : RecyclerView.ViewHolder(itemLayout)
    }
}