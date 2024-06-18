package com.example.eviene.interfaces

import com.example.eviene.LoginRequest
import com.example.eviene.LoginResponse
import com.example.eviene.models.Post
import com.example.eviene.models.User
import okhttp3.MultipartBody
import okhttp3.RequestBody

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search")
    fun searchProfiles(@Query("query") query: String): Call<List<User>>

    @POST("/user/register")
    fun registerUser(@Body user: User): Call<Void>

    @POST("/user/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @Multipart
    @POST("/post")
    fun createPost(
        @Header("Authorization") token: String,
        @Part("content") content: RequestBody?,
        @Part images: MultipartBody.Part?
    ): Call<Void>

    @GET("/post")
    fun getPosts(@Header("Authorization") authToken: String): Call<List<Post>>

}
