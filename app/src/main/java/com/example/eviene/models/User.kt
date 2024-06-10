package com.example.eviene.models

data class User(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val profilePicture: String?,
    val bio: String?,
    val posts: List<String>,
    val eventAttended: List<String>,
    val followers: List<String>,
    val following: List<String>,
    val active: Boolean
)