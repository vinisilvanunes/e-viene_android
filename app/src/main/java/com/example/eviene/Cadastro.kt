package com.example.eviene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Cadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val btnContinuar=findViewById<Button>(R.id.btnContinuar)

        btnContinuar.setOnClickListener{
            val nextPage = Intent(this,Cadastro2::class.java);
            startActivity(nextPage);
            finish();
        }

    }
}