package com.example.stylesync.ui.register

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
import com.example.stylesync.databinding.ActivityRegisterBinding
import com.example.stylesync.ui.ViewModelFactory
import com.example.stylesync.ui.login.LoginActivity
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
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
        binding.buttonRegister.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val name = binding.editTextName.text.toString()
            val password = binding.editTextPassword.text.toString()

            lifecycleScope.launch {
                try {
                    val response = viewModel.register(name, email, password)

                    AlertDialog.Builder(this@RegisterActivity).apply {
                        setTitle("Yeah!")
                        setMessage(response.message.toString())
                            setPositiveButton("Login") { _, _ ->
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                            finish()
                        }
                        create()
                        show()
                    }
                } catch (e: Exception) {
                    Log.e("Register", "Error: ${e.message}", e)
                    AlertDialog.Builder(this@RegisterActivity).apply {
                        setTitle("Yah!")
                        setMessage("Terjadi ${e.message}, silahkan ulangi proses registrasi!")
                        setPositiveButton("Ulangi") { _, _ ->
                            finish()
                        }
                        create()
                        show()
                    }
                }
            }
        }

        binding.buttonLogin.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
    }
}