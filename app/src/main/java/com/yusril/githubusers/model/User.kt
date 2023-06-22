package com.yusril.githubusers.model

data class User(
    val id: Int,
    val login: String,
    val name: String?,
    val avatarUrl: String,
)
