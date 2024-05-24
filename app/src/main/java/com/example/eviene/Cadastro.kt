package com.example.eviene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.eviene.databinding.ActivityCadastroBinding

class Cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinuar.setOnClickListener{
            val i = Intent(this,Cadastro2::class.java);
            startActivity(i);
            finish();
        }
        binding.btnRetornarCadastro.setOnClickListener{
            val i = Intent(this,Login::class.java);
            startActivity(i);
            finish();
        }

    }
}