package com.example.eviene.interfaces

import com.example.eviene.models.User
import com.example.eviene.LoginRequest
import com.example.eviene.LoginResponse
import com.example.eviene.PostResponse
import com.example.eviene.UpdateUserResponse
import com.example.eviene.models.Post
import com.example.eviene.models.UserInfos
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("/user/search")
    fun searchProfiles(@Query("q") query: String): Call<List<User>>

    @POST("/user/register")
    fun registerUser(@Body user: User): Call<Void>

    @POST("/user/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @Multipart
    @POST("/post")
    fun createPost(
        //@Header("Authorization") token: String,
        @Part("content") content: RequestBody?,
        @Part images: MultipartBody.Part?
    ): Call<Void>

    @GET("/post")
    fun getPosts(): Call<List<Post>>

    @Multipart
    @PUT("/")
    fun updateUser(
        @Part("username") username: RequestBody,
        @Part("name") name: RequestBody,
        @Part("bio") bio: RequestBody?,
        @Part("birthDate") birthDate: RequestBody?,
        @Part profilePic: MultipartBody.Part?
    ): Call<UpdateUserResponse>

    @GET("user/info/{username}")
    suspend fun getUser(@Path("username") username: String): UserInfos

    @PUT("user/follow/{username}")
    suspend fun followUser(@Path("username") username: String): Call<UpdateUserResponse>

    @GET("user/info")
    suspend fun getLoggedUser(): UserInfos

}
