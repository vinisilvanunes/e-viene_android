package com.example.eviene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

//        val txtLogCadastrarSe=findViewById<Button>(R.id.txtLogCadastrarSe)
        val btnLogar=findViewById<Button>(R.id.btnLogEntrar)

        btnLogar.setOnClickListener{
            val nextPage = Intent(this,MainActivity::class.java);
            startActivity(nextPage);
            finish();
        }
//        txtLogCadastrarSe.setOnClickListener{
//            val nextPage = Intent(this,Cadastro::class.java);
//            startActivity(nextPage);
//            finish();
//        }

    }
}