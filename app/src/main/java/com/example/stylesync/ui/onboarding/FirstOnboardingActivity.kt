package com.example.stylesync.ui.onboarding

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import com.example.stylesync.R
import com.example.stylesync.databinding.ActivityFirstOnboardingBinding
import com.example.stylesync.ui.register.RegisterActivity

class FirstOnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirstOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()

        binding.button.setOnClickListener {
            startActivity(Intent(this, SecondOnboardingActivity::class.java))
            finish()
        }

        binding.skip1.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
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
}