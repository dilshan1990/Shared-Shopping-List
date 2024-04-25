package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Handler to start the SignInActivity and close this SplashActivity after some seconds.
        Handler().postDelayed({
            startActivity(Intent(this, SignInActivity::class.java))
            finish()  // Close the activity so the user won't able to go back this when pressing Back button.
        }, 3000)  // Delay in milliseconds
    }
}
