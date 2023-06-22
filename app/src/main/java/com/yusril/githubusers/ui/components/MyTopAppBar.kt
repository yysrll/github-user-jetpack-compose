package com.yusril.githubusers.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MyTopAppBar(
    title: String,
    isHaveBackButton: Boolean = false,
    navigateBack: () -> Unit,
    isHaveAction: Boolean = false,
    action: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(if (isHaveBackButton) 4.dp else 0.dp)
            )
        },
        navigationIcon = if (isHaveBackButton) {
                {
                    IconButton(onClick = {
                        navigateBack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "back"
                        )
                    }
                }
            } else {
                null
            },
        actions = {
            if (isHaveAction) {
                IconButton(onClick = {
                    action()
                }) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "about_page"
                    )
                }
            }
        }
    )
}