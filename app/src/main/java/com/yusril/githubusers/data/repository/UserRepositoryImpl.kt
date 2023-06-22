package com.yusril.githubusers.data.repository

import com.yusril.githubusers.data.network.RetrofitConfig
import com.yusril.githubusers.model.User
import com.yusril.githubusers.model.UserDetail
import com.yusril.githubusers.vo.Resource
import org.json.JSONObject
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class UserRepositoryImpl : UserRepository {

    override suspend fun searchUsers(q: String): Resource<List<User>> {
        return try {
            val response = RetrofitConfig.apiService.searchUsers(q)
            if (response.isSuccessful) {
                val result = response.body()?.items
                if (result?.isNotEmpty() == true) {
                    val usersWithName = result.map {
                        val user = RetrofitConfig.apiService.getUser(it.login)
                        if (user.isSuccessful) {
                            user.body()
                        } else {
                            it
                        }
                    }
                    Resource.success(usersWithName as List<User>)
                } else {
                    Resource.empty()
                }
            } else {
                val errorMsg = JSONObject(response.errorBody()?.string()!!)
                Resource.error(errorMsg.getString("message"))
            }
        } catch (e: UnknownHostException) {
            Resource.error(e.message.toString())
        } catch (e: SocketTimeoutException) {
            Resource.error(e.message.toString())
        }
    }

    override suspend fun getDetailUser(username: String): Resource<UserDetail> {
         return try {
            val response = RetrofitConfig.apiService.getDetailUser(username)
            if (response.isSuccessful) {
                if (response.body() != null) {
                    Resource.success(response.body()!!)
                } else {
                    Resource.empty()
                }
            } else {
                val errorMsg = JSONObject(response.errorBody()?.string()!!)
                Resource.error(errorMsg.getString("message"))
            }
        } catch (e: Exception) {
            Resource.error(e.message.toString())
        }
    }


    companion object {
        private var INSTANCE: UserRepositoryImpl? = null

        fun getInstance(): UserRepositoryImpl {
            if (INSTANCE == null) {
                INSTANCE = UserRepositoryImpl()
            }
            return INSTANCE!!
        }
    }

}