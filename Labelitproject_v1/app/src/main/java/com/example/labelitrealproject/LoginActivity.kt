package com.example.labelitrealproject

//import com.google.android.gms.auth.api.Auth
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInAccount
//import com.google.android.gms.auth.api.signin.GoogleSignInClient
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//api받는 라이브러
//import com.google.firebase.auth.GoogleAuthProvider
//import kotlinx.android.synthetic.main.activity_add_photo.view.*
//import com.facebook.CallbackManager
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
//    private lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val emailEditText = findViewById<EditText>(R.id.et_email)
        val passwordEditText = findViewById<EditText>(R.id.et_password)
        val loginButton = findViewById<Button>(R.id.btn_sign_in)
        val signUpButton = findViewById<TextView>(R.id.btn_sign_up)
        val forgot_password_Button = findViewById<TextView>(R.id.btn_forgot_password)


        forgot_password_Button.setOnClickListener {
            forgotPassword()
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            // 이메일과 패스워드를 JSON 형태로 묶고, API URL에다 넣어서 보내는 코드

            // 받은 결과물로 예외처리 진행하는 코드


            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        successLogin()
                    } else {
                        Toast.makeText(
                            this,
                            "로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        emailEditText.addTextChangedListener {
            val enable =
                emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty() // text가 비어 있을 떄는 작동안하도록 설정
            loginButton.isEnabled = enable
            signUpButton.isEnabled = enable

            if (loginButton.isEnabled) {

                loginButton.setBackgroundColor(Color.parseColor("#9DE8F7"))
            } else {
                loginButton.setBackgroundColor(Color.parseColor("#B1B1B1"))
            }
        }

        passwordEditText.addTextChangedListener {
            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
            loginButton.isEnabled = enable
            signUpButton.isEnabled = enable

            if (loginButton.isEnabled) {

                loginButton.setBackgroundColor(Color.parseColor("#9DE8F7"))
            } else {
                loginButton.setBackgroundColor(Color.parseColor("#B1B1B1"))
            }
        }



        signUpButton.setOnClickListener {


            var intent = Intent(this, SignUpAgreeActivity::class.java)
            startActivity(intent)
        }
    }


    private fun forgotPassword() {

        val emailEditText = findViewById<EditText>(R.id.et_email)
        val emailReset = emailEditText.text.toString().trim()
        if (emailReset.isNotEmpty()) {
            FirebaseAuth.getInstance().sendPasswordResetEmail(emailReset).addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Please check your email to reset password",
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else {
                    Toast.makeText(
                        this,
                        "error occurred",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            }
        } else {
            Toast.makeText(
                this,
                "Please enter valid email",
                Toast.LENGTH_LONG
            ).show()
        }
    }








    private fun successLogin() {
        if (auth.currentUser == null) {
            Toast.makeText(this, "로그인에 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        val userId: String = auth.currentUser!!.uid
        val currentUserDb = Firebase.database.reference.child("Users").child(userId)
        // 데이터베이스 객체를 만들어서 users까지 접근합니다.
        //그리고 child 메소드로 하위 트리 구조에 접근해서 해당 레벨의 키에 값을 지정해줍니다.
        val user = mutableMapOf<String, Any>()
        user["userId"] = userId
        currentUserDb.updateChildren(user)
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}








