package com.sryang.topappbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarProvider.SimpleTopAppBar() {
    TopAppBar(
        title = {
            Text("Top app bar")
        }
    )
}

@Preview
@Composable
fun PreviewScaffoldTopAppBar() {
    TopAppBarProvider.SimpleTopAppBar()
}
