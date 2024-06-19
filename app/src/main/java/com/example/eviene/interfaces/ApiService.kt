package com.example.eviene.interfaces

import com.example.eviene.LoginRequest
import com.example.eviene.LoginResponse
import com.example.eviene.PostResponse
import com.example.eviene.models.Post
import com.example.eviene.models.User
import com.example.eviene.models.UserInfos
import okhttp3.MultipartBody
import okhttp3.RequestBody

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("user/search")
        fun searchProfiles(@Query("q") query: String): Call<List<User>>

    @GET("user/info/{username}")
    suspend fun getUser(@Path("username") username: String): UserInfos

    @GET("user/info")
    suspend fun getLoggedUser(): UserInfos

    @POST("/user/register")
    fun registerUser(@Body user: User): Call<Void>

    @POST("/user/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @Multipart
    @POST("/posts")
    fun createPost(
        @Header("Authorization") token: String,
        @Part("text") text: RequestBody?,
        @Part image: MultipartBody.Part?
    ): Call<Void>

    @GET("/posts")
    fun getPosts(@Header("Authorization") token: String): Call<List<Post>>

}
