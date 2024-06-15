package com.example.eviene

data class PostResponse(
val id: String,
val userId: String,
val text: String?,
val imageUrl: String?,
val timestamp: String,
val token: String
)
