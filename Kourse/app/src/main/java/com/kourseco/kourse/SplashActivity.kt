package com.kourseco.kourse

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.kourseco.kourse.introduction_screen.MainActivity
import com.kourseco.kourse.databinding.ActivitySplashBinding
import kotlin.concurrent.thread

class SplashActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        // initialize firebase
        auth = FirebaseAuth.getInstance()

        thread {
            Thread.sleep((3 * 1000).toLong())
            if (auth.currentUser == null)
                startMainActivity()
            else
                startHomeActivity()
        }.priority = Thread.NORM_PRIORITY
    }

    private fun startHomeActivity() {
        val intent = Intent(applicationContext, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startMainActivity() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
