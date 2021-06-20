package com.example.labelitrealproject

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up_phone_verify.*

class SignUpPhoneVerifyActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_phone_verify)

        val phoneNumber =  findViewById<EditText>(R.id.et__phone_number_input_text)


                    btn_sign_up_phone_verify_back.setOnClickListener {
                        finish()
                    }

                    btn_sign_up_phone_verify_next.setOnClickListener {
                        var intent: Intent = Intent(this, SignUpActivity::class.java)
                        startActivity(intent)
                    }
                }
            }