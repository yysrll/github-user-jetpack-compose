package com.yusril.githubusers.data.repository

import com.yusril.githubusers.model.User
import com.yusril.githubusers.model.UserDetail
import com.yusril.githubusers.vo.Resource

interface UserRepository {
    suspend fun searchUsers(q: String) : Resource<List<User>>
    suspend fun getDetailUser(username: String) : Resource<UserDetail>
}