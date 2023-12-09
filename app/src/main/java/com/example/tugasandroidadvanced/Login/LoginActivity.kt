package com.example.tugasandroidadvanced.Login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.tugasandroidadvanced.MainActivity
import com.example.tugasandroidadvanced.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val sharepreference = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val getUsername = sharepreference.getString("USERNAME","")
        val getPassword = sharepreference.getString("PASSWORD","")

        if(getUsername != ""&& getPassword != ""){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        val btnLogin = findViewById<Button>(R.id.btn_login)
        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        btnLogin.setOnClickListener{
            val username = username.text.toString()
            val password = password.text.toString()

            val editor = sharepreference.edit()
            editor.putString("USERNAME",username)
            editor.putString("PASSWORD",password)
            editor.apply()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)

        }
    }
}