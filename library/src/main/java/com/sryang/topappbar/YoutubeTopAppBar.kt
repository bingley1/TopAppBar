package com.sryang.topappbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarProvider.YoutubeTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    TopAppBar(
        title = { Text(text = "YouTube", fontWeight = FontWeight.Bold) },
        navigationIcon = {
            Image(
                modifier = Modifier.width(38.dp),
                painter = painterResource(id = R.drawable.ic_youtube),
                contentDescription = ""
            )
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.width(25.dp),
                    painter = painterResource(id = R.drawable.ic_yt_action1),
                    contentDescription = ""
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.width(25.dp),
                    painter = painterResource(id = R.drawable.ic_yt_action2),
                    contentDescription = ""
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.width(25.dp),
                    painter = painterResource(id = R.drawable.ic_yt_action3),
                    contentDescription = ""
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewYoutubeTopAppBar() {
    TopAppBarProvider.YoutubeTopAppBar()
}