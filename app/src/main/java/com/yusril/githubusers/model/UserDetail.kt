package com.yusril.githubusers.model

data class UserDetail(
    val id: Int,
    val login: String,
    val name: String,
    val location: String?,
    val avatarUrl: String,
    val company: String?,
    val bio: String?,
    val publicRepos: Int,
    val followers: Int,
    val following: Int,
)
