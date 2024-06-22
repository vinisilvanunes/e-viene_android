package com.example.eviene.models

import com.google.gson.annotations.SerializedName
import java.sql.Date

data class Post(
    @SerializedName("_id") val id: String,
    @SerializedName("author") val author: Author,
    @SerializedName("images") val images: List<String>,
    @SerializedName("description") val description: String,
    @SerializedName("likes") val likes: List<String>,
    @SerializedName("visible") val visible: Boolean,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("comments") val comments: List<String>,
    @SerializedName("__v") val v: Int
)

data class Author(
    @SerializedName("_id") val id: String,
    @SerializedName("username") val username: String,
    val profilePicture: String?
)

