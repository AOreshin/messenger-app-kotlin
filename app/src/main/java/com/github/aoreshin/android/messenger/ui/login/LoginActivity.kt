package com.github.aoreshin.android.messenger.ui.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.github.aoreshin.android.messenger.R
import com.github.aoreshin.android.messenger.ui.data.local.AppPreferences
import com.github.aoreshin.android.messenger.ui.main.MainActivity
import com.github.aoreshin.android.messenger.ui.signup.SignUpActivity

class LoginActivity : AppCompatActivity(), LoginView, View.OnClickListener {
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignUp: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: LoginPresenter
    private lateinit var preferences: AppPreferences
    private val requestCode = 567823

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindViews()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setUsernameError() {
        etUsername.error = resources.getString(R.string.empty_login)
    }

    override fun setPasswordError() {
        etPassword.error = resources.getString(R.string.empty_password)
    }

    override fun navigateToSignUp() {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    override fun navigateToHome() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun bindViews() {
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_login)
        btnSignUp = findViewById(R.id.btn_sign_up)
        progressBar = findViewById(R.id.progress_bar)

        presenter = LoginPresenterImpl(this)
        btnLogin.setOnClickListener(this)
        btnSignUp.setOnClickListener(this)
    }

    override fun getContext(): Context = this

    override fun showAuthError() {
        Toast.makeText(this, R.string.invalid_credentials, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btn_login) {
            presenter.executeLogin(etUsername.text.toString(), etPassword.text.toString())
        } else if (view.id == R.id.btn_sign_up) {
            navigateToSignUp()
        }
    }
}
