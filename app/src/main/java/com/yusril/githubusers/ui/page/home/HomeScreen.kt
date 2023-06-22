package com.yusril.githubusers.ui.page.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yusril.githubusers.model.User
import com.yusril.githubusers.ui.components.MyTopAppBar
import com.yusril.githubusers.ui.components.SearchTextField
import com.yusril.githubusers.ui.components.UserCard
import com.yusril.githubusers.vo.Resource
import com.yusril.githubusers.vo.Status

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel= viewModel(),
    navigateToDetail: (String) -> Unit,
    navigateToProfile: () -> Unit
) {
    val users = viewModel.users.collectAsState()

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Github App",
                navigateBack = {},
                isHaveAction = true,
                action = navigateToProfile
            )
        }
    ) {
        HomeScreenBody(
            users.value,
            viewModel::searchUsers,
            navigateToDetail = navigateToDetail
        )
    }
}


@Composable
fun HomeScreenBody(
    users: Resource<List<User>>,
    onQueryChange: (String) -> Unit,
    navigateToDetail: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column {
            SearchTextField(onSearchInputChange = onQueryChange)
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                when (users.status) {
                    Status.LOADING -> CircularProgressIndicator()
                    Status.SUCCESS -> users.data?.let {
                        ListUser(
                            users = it,
                            navigateToDetail = navigateToDetail
                        )
                    }
                    Status.EMPTY -> Text(text = "user not found")
                    Status.ERROR -> Text(text = users.message.toString())
                }
            }
        }
    }
}

@Composable
fun ListUser(
    users: List<User>,
    navigateToDetail: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(users) { user ->
            UserCard(
                user = user,
                navigateToDetail = navigateToDetail
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToDetail = {},
        navigateToProfile = {}
    )
}