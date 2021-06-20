package com.example.labelitrealproject

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        auth = Firebase.auth


        val signUpDoneButton = findViewById<TextView>(R.id.btn_sign_up_done)
        val signUpBackButton = findViewById<ImageView>(R.id.btn_sign_up_back)
        val signUpEmailVerifyButton = findViewById<Button>(R.id.btn_sign_up_duplicate_verify)

        val emailInputEditText = findViewById<EditText>(R.id.et_email_input_text)
        val passwordInputEditText = findViewById<EditText>(R.id.et_password_input_text)
        val nicknameInputEditText = findViewById<EditText>(R.id.et_nickname_input_text)
        val birthdayInputEditText = findViewById<EditText>(R.id.et_birthday_input_text)

        // 검사 정규식
        val emailValidation =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"


        // addTextChangedListener의 경우 익명클래스이니 필수 함수들을 import 해줘야 함
        emailInputEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                // text가 변경된 후 호출
                // s에는 변경 후의 문자열이 담겨 있다.
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // text가 변경되기 전 호출
                // s에는 변경 전 문자열이 담겨 있다.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // text가 바뀔 때마다 호출된다.
                // 우린 이 함수를 사용한다.
                checkEmail()
            }
        })

        emailInputEditText.addTextChangedListener {

            val enable =
                emailInputEditText.text.isNotEmpty() && passwordInputEditText.text.isNotEmpty()
                        && nicknameInputEditText.text.isNotEmpty() && birthdayInputEditText.text.isNotEmpty() // text가 비어 있을 떄는 작동안하도록 설정

            signUpDoneButton.isEnabled = enable

        }

        passwordInputEditText.addTextChangedListener {

            val enable =
                emailInputEditText.text.isNotEmpty() && passwordInputEditText.text.isNotEmpty()
                        && nicknameInputEditText.text.isNotEmpty() && birthdayInputEditText.text.isNotEmpty() // text가 비어 있을 떄는 작동안하도록 설정

            signUpDoneButton.isEnabled = enable

        }

        nicknameInputEditText.addTextChangedListener {

            val enable =
                emailInputEditText.text.isNotEmpty() && passwordInputEditText.text.isNotEmpty()
                        && nicknameInputEditText.text.isNotEmpty() && birthdayInputEditText.text.isNotEmpty() // text가 비어 있을 떄는 작동안하도록 설정

            signUpDoneButton.isEnabled = enable

        }

        birthdayInputEditText.addTextChangedListener {

            val enable =
                emailInputEditText.text.isNotEmpty() && passwordInputEditText.text.isNotEmpty()
                        && nicknameInputEditText.text.isNotEmpty() && birthdayInputEditText.text.isNotEmpty() // text가 비어 있을 떄는 작동안하도록 설정

            signUpDoneButton.isEnabled = enable

        }






        signUpDoneButton.setOnClickListener {


            val email = emailInputEditText.text.toString()
            val password = passwordInputEditText.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this,
                            "회원가입을 성공했습니다. 메인 화면으로 넘어가겠습니다.",
                            Toast.LENGTH_SHORT
                        ).show()

                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, "이미 가입한 이메일이거나, 회원가입에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }

        signUpBackButton.setOnClickListener {
            finish()
        }


        btn_email_input_text_clear.setOnClickListener {
            emailInputEditText.clear()
        }



        signUpEmailVerifyButton.setOnClickListener {

            checkEmailExistsOrNot()
        }
    }


    fun checkEmail(): Boolean {

        val emailInputEditText = findViewById<EditText>(R.id.et_email_input_text)
        // 검사 정규식
        val emailValidation =
            "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"

        var email = emailInputEditText.text.toString().trim() //공백제거
        val p = Pattern.matches(emailValidation, email) // 서로 패턴이 맞닝?
        if (p) {
            //이메일 형태가 정상일 경우
            emailInputEditText.setTextColor(R.color.black.toInt())
            btn_sign_up_duplicate_verify.isEnabled = true

            return true
        } else {
            emailInputEditText.setTextColor(-65536)
            btn_sign_up_duplicate_verify.isEnabled = false
            //또는 questionEmail.setTextColor(R.color.red.toInt())
            return false
        }
    }


    fun checkEmailExistsOrNot() {
        val emailInputEditText = findViewById<EditText>(R.id.et_email_input_text)
        auth.fetchSignInMethodsForEmail(emailInputEditText.getText().toString())
            .addOnCompleteListener(
                OnCompleteListener<SignInMethodQueryResult> { task ->
                    Log.d(TAG, "" + task.result!!.signInMethods!!.size)
                    if (task.result!!.signInMethods!!.size == 0) {
                        //email doesn't exist
                        Toast.makeText(applicationContext, "사용가능 이메일", Toast.LENGTH_LONG).show()
                    } else {
                        // email existed
                        Toast.makeText(applicationContext, "다른 사용자가 이미 사용중인 이메일", Toast.LENGTH_LONG)
                            .show()
                    }
                }).addOnFailureListener(OnFailureListener { e -> e.printStackTrace() })
    }


//    // 비밀번호 유효성 검사
//    private fun isValidPasswd(): Boolean {
//        val passwordInputEditText = findViewById<EditText>(R.id.et_password_input_text)
//
//        // 비밀번호 정규식
//        val PASSWORD_PATTERN = Pattern.compile("^[a-zA-Z0-9!@.#$%^&*?_~]{4,16}$");
//        if (!PASSWORD_PATTERN.matcher(passwordInputEditText.toString()).matches()) {
//            // 비밀번호 형식 불일치
//            false
//        }
//    }


    fun EditText.clear() {
        text.clear()
    }

}


