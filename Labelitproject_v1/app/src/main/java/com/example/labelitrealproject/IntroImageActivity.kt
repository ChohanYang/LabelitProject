package com.example.labelitrealproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class IntroImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView (R.layout.activity_introimage)

        var handler = Handler()
        handler.postDelayed ({
            var intent = Intent(this, LoginActivity::class.java)
            startActivity (intent)
        }, 2000) //2초 뒤 이미지 보여줌
    }

    override fun onPause() {
        super.onPause()
        finish ()
    }
}
