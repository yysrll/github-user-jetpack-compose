package com.yusril.githubusers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.yusril.githubusers.ui.navigation.Screen
import com.yusril.githubusers.ui.page.aboutme.AboutScreen
import com.yusril.githubusers.ui.page.detail.DetailScreen
import com.yusril.githubusers.ui.page.home.HomeScreen

@Composable
fun GithubUsersApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToDetail = { username ->
                    navController.navigate(Screen.Detail.createRoute(username))
                },
                navigateToProfile = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("username") {type = NavType.StringType})
        ) {
            val username = it.arguments?.getString("username") ?: "username not found"
            DetailScreen(
                username = username,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(
            route = Screen.Profile.route,
        ) {
            AboutScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}