package com.sryang.topappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.topappbar.ui.theme.TopAppBarTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val topPageState = rememberPagerState { topAppBarTypeList.size }
            var selectedTopAppBar by remember { mutableStateOf(topAppBarTypeList[0]) }

            snapshotFlow { topPageState }

            LaunchedEffect(key1 = "") {
                snapshotFlow {
                    topPageState.currentPage
                }.collect {
                    selectedTopAppBar = topAppBarTypeList[it]
                }
            }

            val scrollBehavior =
                TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
            TopAppBarTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = selectedTopAppBar.topBar
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
                Box(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.align(Alignment.Center)) {
                        SelectTopAppBar(topPageState = topPageState)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun SelectTopAppBar(topPageState: PagerState) {
        val height = 50.dp
        Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "TopBar:")
            VerticalPager(state = topPageState) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = topAppBarTypeList[it].name,
                        modifier = Modifier.align(Alignment.Center)
                    )
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
    TopAppBarProvider.SimpleTopAppBar()
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