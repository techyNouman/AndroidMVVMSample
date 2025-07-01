package com.example.androidmvvmsample.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmvvmsample.R
import com.example.androidmvvmsample.data.model.SignInRequest
import com.example.androidmvvmsample.presentation.viewmodel.AuthViewModel
import com.example.androidmvvmsample.utils.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModels()
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val loginButton = findViewById<Button>(R.id.btnLogin)
        val emailField = findViewById<EditText>(R.id.etEmail)
        val passwordField = findViewById<EditText>(R.id.etPassword)
        progressBar = findViewById(R.id.progressBar)

        loginButton.setOnClickListener {
            val email = emailField.text.toString()
            val password = passwordField.text.toString()
            if (email.isEmpty()){
                showToast("Enter email")
            }else if (password.isEmpty()){
                showToast("Enter password")
            }else{
                viewModel.signIn(SignInRequest(email, password))
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.signInResult.observe(this) { state ->
            when (state) {
                is UIState.Loading -> showLoading()
                is UIState.Success -> {
                    hideLoading()
                    goToHomeScreen()
                }

                is UIState.Error -> {
                    hideLoading()
                    showToast(state.message)
                }

                else -> {}
            }
        }
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun goToHomeScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}