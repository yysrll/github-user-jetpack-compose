package com.yusril.githubusers.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StatisticCard(
    modifier: Modifier = Modifier,
    total: String,
    description: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondary)
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = total,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onSecondary
        )
        Text(
            text = description,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.onSecondary
        )
    }
}


@Preview(showBackground = true)
@Composable
fun StatisticCardPreview() {
    StatisticCard(
        total = "12",
        description = "Repositories"
    )
}