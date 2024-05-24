package com.example.eviene.dao


import com.example.eviene.models.Post

interface DaoPost {
    suspend fun insertPost(post: Post)
    suspend fun deletePost(post: Post)

}