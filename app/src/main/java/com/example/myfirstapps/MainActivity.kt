package com.example.myfirstapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import android.content.Intent

//punya AZQYAAA


class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Atur padding layout agar tidak tertutup status bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            return@setOnApplyWindowInsetsListener insets  //
        }

        // nama database
        dbHelper = MyDatabaseHelper(this)

        // Ambil komponen dari layout
        val etUsername = findViewById<EditText>(R.id.txtusername)
        val etPassword = findViewById<EditText>(R.id.txtpw)
        val btnLogin = findViewById<Button>(R.id.btnlogin)

        // ketika tombol login d klik
        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            //ketika pw atau user tidak di isi
            if (username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (dbHelper.cekLogin(username, password)) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                // ketika di user dan pw salah
            } else {
                Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show()
            }

            if (dbHelper.cekLogin(username, password)) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, home::class.java)

                intent.putExtra("username", username)
                startActivity(intent)
                finish() // opsional, agar user tidak bisa balik ke login
            }

        }

        }
    }
