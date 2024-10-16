package com.example.adressbook

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var nameET: EditText
    private lateinit var secondNameET: EditText
    private lateinit var addressET: EditText
    private lateinit var phoneNumberET: EditText

    private lateinit var saveBTN: Button

    private lateinit var addressListLV: ListView

    private val persons = mutableListOf<Person>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameET = findViewById(R.id.nameET)
        secondNameET = findViewById(R.id.secondNameET)
        addressET = findViewById(R.id.addressET)
        phoneNumberET = findViewById(R.id.phoneNumberET)

        saveBTN = findViewById(R.id.saveBTN)

        addressListLV = findViewById(R.id.addressListLV)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, persons)
        addressListLV.adapter = adapter

        saveBTN.setOnClickListener {
            savePerson(adapter)
        }
        addressListLV.setOnItemClickListener { _, _, position, _ ->
            val selectedPerson = persons[position]
            val intent = Intent(this, UserInfo::class.java)
            intent.putExtra("person", selectedPerson)
            startActivity(intent)
        }
    }

    private fun savePerson(adapter: ArrayAdapter<Person>) {
        if (
            nameET.text.isEmpty() ||
            secondNameET.text.isEmpty() ||
            addressET.text.isEmpty() ||
            phoneNumberET.text.isEmpty()
        ) {
            Toast.makeText(this, "Заполните все четыре поля", Toast.LENGTH_LONG).show()
            return
        }
        if (!checkNumber(phoneNumberET.text.toString())) {
            Toast.makeText(this, "Введите корректный номер телефона", Toast.LENGTH_LONG).show()
            return
        }
        val person = Person(
            nameET.text.toString().trim(),
            secondNameET.text.toString().trim(),
            addressET.text.toString().trim(),
            phoneNumberET.text.toString().trim()
        )
        adapter.add(person)
        adapter.notifyDataSetChanged()
        nameET.text.clear()
        secondNameET.text.clear()
        addressET.text.clear()
        phoneNumberET.text.clear()
    }

    private fun checkNumber(number: String): Boolean {
        val regex = Regex("^\\+?[0-9]{10,12}\$")
        return regex.matches(number)
    }
}