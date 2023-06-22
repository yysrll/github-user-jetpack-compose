package com.yusril.githubusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.yusril.githubusers.ui.page.home.HomeScreen
import com.yusril.githubusers.ui.theme.GithubUsersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersTheme {
                GithubUsersApp()
            }
        }
    }
}