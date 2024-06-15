package com.example.eviene

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eviene.databinding.ActivityCadastroBinding

class Cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnContinuar.setOnClickListener{
            val name = binding.txtNome.text.toString()
            val username = binding.txtNomeUsuario.text.toString()
            val birthdate = binding.txtDataNascimento.text.toString()

            val intent = Intent(this@Cadastro, Cadastro2::class.java)
            intent.putExtra("name", name)
            intent.putExtra("username", username)
            intent.putExtra("birthdate", birthdate)
            startActivity(intent)
        }
        binding.btnRetornarCadastro.setOnClickListener{
            val i = Intent(this@Cadastro,Login::class.java);
            startActivity(i);
            finish();
        }

    }
}