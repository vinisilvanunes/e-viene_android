package com.example.eviene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.eviene.databinding.ActivityCadastro2Binding

class Cadastro2 : AppCompatActivity() {

    private lateinit var binding: ActivityCadastro2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastro2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFinalizar.setOnClickListener{
            val i = Intent(this,Login::class.java);
            startActivity(i);
            finish();
        }

        binding.btnRetornarCadastro2.setOnClickListener{
            val i = Intent(this,Cadastro::class.java);
            startActivity(i);
            finish();
        }

    }
}