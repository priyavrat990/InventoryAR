package com.apisod.inventoryar.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.apisod.inventoryar.MainActivity
import com.apisod.inventoryar.R
import com.apisod.inventoryar.common.CentralizedActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : CentralizedActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var ivTogglePassword: ImageView
    private lateinit var progressBar: ProgressBar

    private var isPasswordVisible = false
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase Auth
        auth = getUpdatedFirebaseAuth()

        // Check if user is already logged in
        if (auth.currentUser != null) {
            navigateToHome()
            return
        }

        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        initViews()
        clickListeners()
    }

    private fun initViews() {
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnSignup = findViewById(R.id.btnSignup)
        ivTogglePassword = findViewById(R.id.ivTogglePassword)
        progressBar = findViewById(R.id.progressBar)
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Prevent going back to login screen
    }

    private fun clickListeners() {
        loginButtonClickListener()

        signupButtonClickListener()

        ivToggleClickListener()
    }

    private fun ivToggleClickListener() {
        // Toggle password visibility
        ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            etPassword.inputType =
                if (isPasswordVisible)
                    InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                else
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            etPassword.setSelection(etPassword.text.length)
        }
    }

    private fun loginButtonClickListener() {
        btnLogin.setOnClickListener{
            handleLogin()
        }
    }

    private fun signupButtonClickListener() {
        btnSignup.setOnClickListener {
            launchFirebaseSignup()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun handleLogin() {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (!isValidEmail(email)) {
            etEmail.error = "Enter valid email"
            return
        }

        if (password.isEmpty()) {
            etPassword.error = "Enter password"
            return
        }

        showLoading(true)

        // 🔥 Try login
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                showLoading(false)
                if (task.isSuccessful) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
                    // TODO: Navigate to next screen
                    navigateToHome()
                } else {
                    Toast.makeText(this, "Account not found, please sign up", Toast.LENGTH_SHORT).show()
                    launchFirebaseSignup()
                }
            }
    }

    // FirebaseUI ONLY for signup
    private val signupLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Signup Success", Toast.LENGTH_SHORT).show()
                navigateToHome()
            }
        }

    private fun launchFirebaseSignup() {
        val providers = listOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()

        signupLauncher.launch(intent)
    }

    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }
}
