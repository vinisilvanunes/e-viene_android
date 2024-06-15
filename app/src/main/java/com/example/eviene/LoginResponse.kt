package com.example.eviene

data class LoginResponse(
    val token: String?,
    val userId: String?,
    val message: String
    // Adicione outros campos conforme necessário
)