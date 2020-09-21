package com.vwap.themoviesapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.vstechlab.easyfonts.EasyFonts
import com.vwap.themoviesapp.R
import com.vwap.themoviesapp.utils.setCustomFont
import kotlinx.android.synthetic.main.splash_layout.*
import kotlinx.coroutines.*

const val SPLASH_TIMER = 3000L

class SplashActivity : AppCompatActivity() {
    private lateinit var activityScope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)
        setTypeface(label)
    }

    override fun onResume() {
        super.onResume()
        startTransitionCoroutine()
    }

    override fun onPause() {
        stopTransitionCoroutine()
        super.onPause()
    }

    private fun setTypeface(text: TextView) =
        text.setCustomFont(EasyFonts.captureIt(text.context))

    private fun startTransitionCoroutine() {
        activityScope = CoroutineScope(Dispatchers.Main)
        activityScope.launch {
            delay(SPLASH_TIMER)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    private fun stopTransitionCoroutine() = activityScope.cancel()

}