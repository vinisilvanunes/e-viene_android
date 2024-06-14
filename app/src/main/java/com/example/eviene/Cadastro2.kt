package com.example.eviene

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.eviene.databinding.ActivityCadastro2Binding
import com.example.eviene.models.User

class Cadastro2 : AppCompatActivity() {

    private lateinit var binding: ActivityCadastro2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastro2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val name = intent.getStringExtra("name")!!
        val username = intent.getStringExtra("username")!!
        val birthdate = intent.getStringExtra("birthdate")!!

        binding.btnFinalizar.setOnClickListener {
            val email = binding.txtEmail.text.toString()
            val password = binding.txtSenhaCadastro.text.toString()
            val confirmPassword = binding.txtConfirmarSenha.text.toString()

            if (password != confirmPassword) {
                Toast.makeText(this@Cadastro2, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
    }

    private fun registerUser(
        name: String,
        username: String,
        birthdate: String,
        email: String,
        password: String
    ) {
        val apiService = RetrofitClient.instance

        val user = User(name, username, email, password, birthdate,"","",emptyList(),emptyList(),emptyList(),emptyList(),true)
        val call = apiService.registerUser(user)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@Cadastro2,
                        "Usuário cadastrado",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@Cadastro2,
                        response.message(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@Cadastro2, "Error: " + t.message, Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

}