package com.example.eviene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.eviene.databinding.LoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val txtLogCadastrarSe=findViewById<TextView>(R.id.txtLogCadastrarSe)
        val btnLogar=findViewById<Button>(R.id.btnLogEntrar)

        btnLogar.setOnClickListener{
            val i = Intent(this,MainActivity::class.java);
            startActivity(i);
            finish();
        }
        txtLogCadastrarSe.setOnClickListener{
            val i = Intent(this,Cadastro::class.java);
            startActivity(i);
            finish();
        }

    }
}