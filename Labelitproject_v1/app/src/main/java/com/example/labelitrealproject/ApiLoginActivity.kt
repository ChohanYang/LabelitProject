//package com.example.labelitrealproject
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class ApiLoginActivity  : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        val builder: Retrofit.Builder = Retrofit.Builder()
//            .baseUrl("http://3.128.95.125:8000/")
//            .addConverterFactory(GsonConverterFactory.create())
//        val retrofit: Retrofit = builder.build()
//    }
//}
//
