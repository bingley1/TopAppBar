package com.sryang.topappbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarProvider.ScaffoldTopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            /*containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,*/
        ),
        title = {
            Text("Top app bar")
        }
    )
}

@Preview
@Composable
fun PreviewScaffoldTopAppBar() {
    TopAppBarProvider.ScaffoldTopAppBar()
}
