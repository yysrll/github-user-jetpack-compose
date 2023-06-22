package com.yusril.githubusers.ui.page.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.yusril.githubusers.model.UserDetail
import com.yusril.githubusers.ui.components.MyTopAppBar
import com.yusril.githubusers.ui.components.StatisticCard
import com.yusril.githubusers.vo.Status

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    viewModel: DetailViewModel = viewModel(),
    username: String,
    navigateBack: () -> Unit
) {

    val user = viewModel.detailUser.collectAsState().value
    viewModel.getDetailUser(username)
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Profile",
                isHaveBackButton = true,
                navigateBack = navigateBack,
                action = {}
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            when (user.status) {
                Status.LOADING -> CircularProgressIndicator()
                Status.SUCCESS -> user.data?.let { detail -> DetailScreenBody(detail) }
                Status.EMPTY -> Text(text = "user not found")
                Status.ERROR -> Text(text = user.message.toString())
            }
        }

    }
}

@Composable
fun DetailScreenBody(
    user: UserDetail
) {
    Column {
        DetailHeader(
            fullName = user.name,
            username = user.login,
            avatarUrl = user.avatarUrl
        )
        StatisticBody(
            repositories = user.publicRepos.toString(),
            followers = user.followers.toString(),
            following = user.following.toString(),
        )
        InformationItem(
            label = "Location",
            description = user.location.orEmpty().ifEmpty { "No Location" }
        )
        InformationItem(
            label = "Company",
            description = user.company.orEmpty().ifEmpty { "No Company" }
        )
        InformationItem(
            label = "Bio",
            description = user.bio.orEmpty().ifEmpty { "No bio" }
        )
    }
}

@Composable
fun DetailHeader(
    fullName: String,
    username: String,
    avatarUrl: String,
) {
    Box {
        Image(
            painter = rememberAsyncImagePainter(avatarUrl),
            contentDescription = "avatar",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Gray
                        )
                    )
                )
                .align(Alignment.BottomCenter)
        )
        Column(
            modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomStart)
        ) {
            Text(
                text = fullName,
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
            Text(
                text = "@$username",
                style = MaterialTheme.typography.subtitle2,
                color = Color.White
            )
        }
    }
}

@Composable
fun StatisticBody(
    modifier: Modifier = Modifier,
    repositories: String,
    followers: String,
    following: String

) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        StatisticCard(
            modifier = Modifier
                .weight(1f),
            total = repositories,
            description = "Repositories"
        )
        StatisticCard(
            modifier = Modifier
                .weight(1f),
            total = followers,
            description = "Followers"
        )
        StatisticCard(
            modifier = Modifier
                .weight(1f),
            total = following,
            description = "Following"
        )
    }
}

@Composable
fun InformationItem(
    modifier: Modifier = Modifier,
    label: String,
    description: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = description,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        username = "",
        navigateBack = {}
    )
}