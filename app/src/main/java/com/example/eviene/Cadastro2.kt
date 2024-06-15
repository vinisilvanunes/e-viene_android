package com.example.eviene

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.eviene.databinding.ActivityCadastro2Binding
import com.example.eviene.models.User
import kotlin.math.log

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
            registerUser(name=name, birthdate=birthdate, username = username, email = email, password = password)
        }
    }

    private fun registerUser(
        name: String,
        username: String,
        birthdate: String,
        email: String,
        password: String
    ) {
        //val apiService = RetrofitClient.instance
        val birthdate2 = birthdate.split("/")[2] + "-" + birthdate.split("/")[1]  + "-" + birthdate.split("/")[0] + "T00:00:00Z"

        val user = User(name=name, username = username, email = email, password = password,
                birthDate = birthdate2, active = true, bio = null, eventAttended = emptyList(),
                followers = null, following = null, posts = null, profilePicture = null, confirmPassword = password,_id = null)
        //val call = apiService.registerUser(user)
        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY2NjBlOTQ1MzgyM2Q3MDQyY2RlNjhlMiIsImlhdCI6MTcxNzYzMDIxNn0._qZRFTk-oOGRzAAoqmiCYoDTWITEfLx3h0sDcr8gz1U"

        RetrofitClient.getClientNoToken().registerUser(user).enqueue(object : Callback<Void> {
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