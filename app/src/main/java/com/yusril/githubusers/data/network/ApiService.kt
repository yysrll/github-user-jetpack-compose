package com.yusril.githubusers.data.network

import com.yusril.githubusers.data.network.response.SearchUserResponse
import com.yusril.githubusers.model.User
import com.yusril.githubusers.model.UserDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("per_page") size: Int = 10,
    ): Response<SearchUserResponse>

    @GET("users/{username}")
    suspend fun getUser(@Path("username") username: String): Response<User>

    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String): Response<UserDetail>

}