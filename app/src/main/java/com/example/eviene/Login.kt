package com.example.eviene

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.eviene.databinding.LoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Login : AppCompatActivity() {

    private lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogEntrar.setOnClickListener{
            val email = binding.txtLogEmail.text.toString()
            val password = binding.txtLogSenha.text.toString()

            loginUser(email, password)
        }
        binding.txtLogCadastrarSe.setOnClickListener{
            val i = Intent(this,Cadastro::class.java);
            startActivity(i);
            finish();
        }

    }

    private fun loginUser(email: String, password: String) {

        val apiService = RetrofitClient.instance
        val loginRequest = LoginRequest(email, password)
        val call = apiService.loginUser(loginRequest)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.token != null) {
                        // Salvar o token e o ID do usuário conforme necessário
                        PreferencesManager.saveToken(this@Login, loginResponse.token)
                        Toast.makeText(this@Login, "Login successful!", Toast.LENGTH_SHORT).show()
                        // Ir para a próxima Activity
                        val intent = Intent(this@Login, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@Login, loginResponse?.message ?: "Unknown error", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val loginResponse = response.errorBody()?.string()
                    Toast.makeText(this@Login, loginResponse ?: "Invalid credentials", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@Login, "Error: " + t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}