package com.yusril.githubusers.data.network.response

import com.yusril.githubusers.model.User

data class SearchUserResponse(
    val items: List<User>
)
