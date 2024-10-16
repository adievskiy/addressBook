package com.example.adressbook

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserInfo : AppCompatActivity() {

    private lateinit var userInfoTV: TextView
    private lateinit var backBTN: Button
    private var person: Person? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_info)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userInfoTV = findViewById(R.id.userInfoTV)
        backBTN = findViewById(R.id.backBTN)

        person = intent.getParcelableExtra("person")
        val name = person?.name
        val secondName = person?.secondName
        val address = person?.address
        val phoneNumber = person?.phoneNumber
        val text = "Имя: $name\nФамилия: $secondName\nАдрес: $address\nНомер телефона: $phoneNumber"

        userInfoTV.text = text

        backBTN.setOnClickListener {
            onBackPressed()
        }

    }
}