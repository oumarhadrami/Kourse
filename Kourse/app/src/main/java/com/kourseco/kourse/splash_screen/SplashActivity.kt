package com.kourseco.kourse.splash_screen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.kourseco.kourse.R
import com.kourseco.kourse.introduction_screen.MainActivity
import com.kourseco.kourse.databinding.ActivitySplashBinding
import com.kourseco.kourse.home_screens.HomeActivity
import kotlin.concurrent.thread

class SplashActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_splash
        )

        val uri = Uri.parse("android.resource://" + this.packageName + "/" + R.raw.splashvideo)
        binding.video.setVideoURI(uri)
        binding.video.start()

        // initialize firebase
        auth = FirebaseAuth.getInstance()

        thread {
            Thread.sleep((3 * 1000).toLong())
            if (auth.currentUser == null)
            {
                startMainActivity()
                binding.video
            }
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
