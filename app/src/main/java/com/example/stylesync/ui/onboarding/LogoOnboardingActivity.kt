package com.example.stylesync.ui.onboarding

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import com.example.stylesync.R
import com.example.stylesync.ui.ViewModelFactory
import com.example.stylesync.ui.main.MainActivity
import com.example.stylesync.ui.main.MainViewModel

class LogoOnboardingActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo_onboarding)

        setupView()

        viewModel.getSession()

        viewModel.sessionLiveData.observe(this) { user ->
            if (!user.isLogin) {
                @Suppress("DEPRECATION")
                Handler().postDelayed({
                    startActivity(Intent(this, FirstOnboardingActivity::class.java))
                    finish()
                }, 2000)
            } else {
                @Suppress("DEPRECATION")
                Handler().postDelayed({
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }, 2000)
            }
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