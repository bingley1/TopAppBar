package com.sryang.topappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.topappbar.ui.theme.TopAppBarTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scrollBehavior =
                TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            TopAppBarTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBarProvider.FaceBookTopAppBar()
                    }
                ) { innerPadding ->
                    LazyColumn(
                        Modifier
                            .padding(innerPadding)
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                    ) {
                        items(100) {
                            Text(
                                text = "text",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeAppBar() {
    TopAppBarProvider.HomeAppBar()
}

@Preview
@Composable
fun PreviewScaffoldTopAppBar() {
    TopAppBarProvider.ScaffoldTopAppBar()
}

@Preview
@Composable
fun PreviewSurveyTopAppBar() {
    TopAppBarProvider.SurveyTopAppBar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewChannelNameBar() {
    TopAppBarProvider.ChannelNameBar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewTopAppBar() {
    TopAppBarProvider.TestTopAppBar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewYoutubeTopAppBar() {
    TopAppBarProvider.YoutubeTopAppBar()
}

@Preview
@Composable
fun PreviewFaceBookTopAppBar() {
    TopAppBarProvider.FaceBookTopAppBar()
}