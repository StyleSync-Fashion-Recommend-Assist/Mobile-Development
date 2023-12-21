package com.example.stylesync.ui.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.stylesync.R
import com.example.stylesync.data.preference.UserModel
import com.example.stylesync.databinding.ActivityLoginBinding
import com.example.stylesync.ui.ViewModelFactory
import com.example.stylesync.ui.main.MainActivity
import com.example.stylesync.ui.register.RegisterActivity
import com.example.stylesync.ui.register.RegisterViewModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION") if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextEmailLogin.text.toString()
            val password = binding.editTextPasswordLogin.text.toString()

            lifecycleScope.launch {
                try {
                    val response = viewModel.login(email, password)

                    if (response.message == "User berhasil Login" && response.AccessToken != null) {
                        viewModel.saveSession(UserModel(email, response.AccessToken, true))
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    } else {
                        AlertDialog.Builder(this@LoginActivity).apply {
                            setTitle("Yah!")
                            setMessage(response.message)
                            setPositiveButton("Ulangi") { _, _ ->
                                finish()
                            }
                            create()
                            show()
                        }
                    }
                } catch (e: Exception) {
                    Log.e("Register", "Error: ${e.message}", e)
                    AlertDialog.Builder(this@LoginActivity).apply {
                        setTitle("Yah!")
                        setMessage("Terjadi ${e.message}, silahkan ulangi proses login!")
                        setPositiveButton("Ulangi") { _, _ ->
                            finish()
                        }
                        create()
                        show()
                    }
                }
            }
        }

        binding.buttonRegister.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
    }
}