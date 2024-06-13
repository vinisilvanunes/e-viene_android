package com.example.eviene.interfaces

import com.example.eviene.models.User

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    fun searchProfiles(@Query("query") query: String): Call<List<User>>
    @GET("user/{username}")
    suspend fun getUser(@Path("username") username: String): List<User>
}