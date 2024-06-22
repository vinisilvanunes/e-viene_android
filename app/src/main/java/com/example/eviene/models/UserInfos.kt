package com.example.eviene.models

data class UserInfos(
    val profilePic: String,
    val name : String,
    val username: String,
    val followers: String,
    val following: String,
    val bio: String,
    val posts: List<PostUser>
)