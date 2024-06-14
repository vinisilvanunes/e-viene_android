package com.example.eviene.interfaces

import com.example.eviene.models.User
import com.example.eviene.models.UserInfos

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun searchProfiles(@Query("query") query: String): Call<List<User>>
    @GET("user/info/{username}")
    suspend fun getUser(@Path("username") username: String): UserInfos
    @POST("/user/register")
    fun registerUser(@Body user: User): Call<Void>

}
