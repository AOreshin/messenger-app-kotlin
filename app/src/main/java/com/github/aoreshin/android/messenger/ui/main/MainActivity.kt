package com.github.aoreshin.android.messenger.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import com.github.aoreshin.android.messenger.R
import com.github.aoreshin.android.messenger.ui.login.LoginActivity
import com.github.aoreshin.android.messenger.ui.settings.SettingsActivity

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var llContainer: LinearLayout
    private lateinit var presenter: MainPresenter

    private val contactsFragment = ContactsFragment()
    private val conversationsFragment = ConversationsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenterImpl(this)
        conversationsFragment.setActivity(this)
        contactsFragment.setActivity(this)

        bindViews()
        showConversationsScreen()
    }

    override fun bindViews() {
        llContainer = findViewById(R.id.ll_container)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun showConversationsLoadError() {
        Toast.makeText(this, "Unable to load conversations. Try again later.", Toast.LENGTH_LONG).show()
    }

    override fun showContactsLoadError() {
        Toast.makeText(this, "Unable to load contacts. Try again later.", Toast.LENGTH_LONG).show()
    }

    override fun showConversationsScreen() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.ll_container, conversationsFragment)
        fragmentTransaction.commit()
        presenter.loadConversations()
        supportActionBar?.title = "Messenger"
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun showContactsScreen() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.ll_container, contactsFragment)
        fragmentTransaction.commit()
        presenter.loadContacts()
        supportActionBar?.title = "Contacts"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showNoConversations() {
        Toast.makeText(this, "You have no active conversations.", Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> showConversationsScreen()
            R.id.action_settings -> navigateToSettings()
            R.id.action_logout -> presenter.executeLogout()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getContext(): Context {
        return this
    }

    override fun getContactsFragment(): ContactsFragment {
        return contactsFragment
    }

    override fun getConversationsFragment(): ConversationsFragment {
        return conversationsFragment
    }

    override fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun navigateToSettings() {
        startActivity(Intent(this, SettingsActivity::class.java))
    }
}
