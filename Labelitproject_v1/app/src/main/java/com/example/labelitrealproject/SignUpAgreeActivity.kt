package com.example.labelitrealproject

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up_agree.*


class SignUpAgreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_agree)

        //전체동의
        val checkBox = findViewById<CheckBox>(R.id.sign_up_check1)

//필수 서비스이용약관
        val checkBox2 = findViewById<CheckBox>(R.id.sign_up_check2)
//필수 개인정보
        val checkBox3 = findViewById<CheckBox>(R.id.sign_up_check3)
//선택 위치정보
        val checkBox4 = findViewById<CheckBox>(R.id.sign_up_check4)

        val checkBox5 = findViewById<CheckBox>(R.id.sign_up_check5)


        btn_sign_up_agree_back.setOnClickListener {
                        finish()
                    }


//전체동의 클릭시
//전체 true / 전체 false 로 변경
        checkBox.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (checkBox.isChecked) {
                    checkBox2.isChecked = true
                    checkBox3.isChecked = true
                    checkBox4.isChecked = true
                    checkBox5.isChecked = true
                    btn_sign_up_next.setEnabled(true)
                    btn_sign_up_next.setBackgroundColor(Color.parseColor("#9DE8F7"))

                } else {
                    checkBox2.isChecked = false
                    checkBox3.isChecked = false
                    checkBox4.isChecked = false
                    checkBox5.isChecked = false

                    btn_sign_up_next.setEnabled(false)
                    btn_sign_up_next.setBackgroundColor(Color.parseColor("#B1B1B1"))
                }
            }
        })

//2 클릭시
        checkBox2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //만약 전체 클릭이 true 라면 false로 변경
                if (checkBox.isChecked) {
                    checkBox.isChecked = false
                    btn_sign_up_next.setEnabled(false)
                    btn_sign_up_next.setBackgroundColor(Color.parseColor("#B1B1B1"))
                    //각 체크박스 체크 여부 확인해서  전체동의 체크박스 변경
                } else if (checkBox2.isChecked && checkBox3.isChecked && checkBox4.isChecked && checkBox5.isChecked) {
                    checkBox.isChecked = true
                    btn_sign_up_next.setEnabled(true)
                    btn_sign_up_next.setBackgroundColor(Color.parseColor("#9DE8F7"))
                }
            }
        })
//3 클릭시

        checkBox3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (checkBox.isChecked) {
                    checkBox.isChecked = false
                    btn_sign_up_next.setEnabled(false)
                    btn_sign_up_next.setBackgroundColor(Color.parseColor("#B1B1B1"))
                } else if (checkBox2.isChecked && checkBox3.isChecked && checkBox4.isChecked && checkBox5.isChecked) {
                    checkBox.isChecked = true
                    btn_sign_up_next.setEnabled(true)
                    btn_sign_up_next.setBackgroundColor(Color.parseColor("#9DE8F7"))
                }
            }
        })

        //4클릭시 (checkbox 4 까지만 눌러도 전체동의 check 되게끔)
        checkBox4.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (checkBox.isChecked) {
                    checkBox.isChecked = false
                    btn_sign_up_next.setEnabled(false)
                    btn_sign_up_next.setBackgroundColor(Color.parseColor("#B1B1B1"))
                } else if (checkBox2.isChecked && checkBox3.isChecked && checkBox4.isChecked) {
                    checkBox.isChecked = true
                    btn_sign_up_next.setEnabled(true)
                    btn_sign_up_next.setBackgroundColor(Color.parseColor("#9DE8F7"))
                }
            }
        })
        //5클릭시
        checkBox5.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                 if (checkBox2.isChecked && checkBox3.isChecked && checkBox4.isChecked) {
                    checkBox.isChecked = true
                }
            }
        })

        btn_sign_up_next.setOnClickListener {
            var barintent = Intent(this, SignUpPhoneVerifyActivity::class.java)
            startActivity(barintent)
        }

    }



}



