package com.rizkysiregar.mymovieapp.spalsh

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.rizkysiregar.mymovieapp.R
import com.rizkysiregar.mymovieapp.home.HomeActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val FAKE_LOAD_TIME: Long = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }, FAKE_LOAD_TIME )
    }
}