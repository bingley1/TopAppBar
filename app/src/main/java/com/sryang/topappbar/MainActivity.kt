package com.sryang.topappbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sryang.library.ThemeProvider
import com.sryang.library.ThemeTypes
import com.sryang.library.Yellow
import com.sryang.library.themeTypeList
import com.sryang.topappbar.ui.theme.TopAppBarTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val topPageState = rememberPagerState { topAppBarTypeList.size }
            var selectedTopAppBar by remember { mutableStateOf(topAppBarTypeList[0]) }
            var appBarSize: AppBarSize by remember { mutableStateOf(AppBarSize.CenterAligned) }
            var title: String by remember { mutableStateOf("") }
            var navigation: Boolean by remember { mutableStateOf(false) }
            var actions: Int by remember { mutableIntStateOf(0) }
            var left: Int by remember { mutableIntStateOf(0) }
            var right: Int by remember { mutableIntStateOf(0) }
            var top: Int by remember { mutableIntStateOf(0) }
            var bottom: Int by remember { mutableIntStateOf(0) }
            var scroll: Int by remember { mutableIntStateOf(0) }
            val themePageState = rememberPagerState { themeTypeList.size }
            var theme: ThemeTypes by remember { mutableStateOf(themeTypeList[0]) }


            LaunchedEffect(key1 = "") {
                snapshotFlow {
                    topPageState.currentPage
                }.collect {
                    selectedTopAppBar = topAppBarTypeList[it]
                }
            }

            LaunchedEffect(key1 = "") {
                snapshotFlow {
                    themePageState.currentPage
                }.collect {
                    theme = themeTypeList[it]
                }
            }

            val scrollBehavior: TopAppBarScrollBehavior? =
                when (scroll) {
                    0 -> null
                    1 -> TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
                    2 -> TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
                    3 -> TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
                    else -> null
                }
            TopAppBarTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentWindowInsets = WindowInsets(left = 30, right = 30),
                    topBar =
                    {
                        theme.contents {
                            if (selectedTopAppBar.topBar != null)
                                selectedTopAppBar.topBar?.invoke()
                            else {
                                appBarSize.compose.invoke(
                                    title,
                                    navigation,
                                    { getActions(actions).invoke() },
                                    WindowInsets(left, top, right, bottom),
                                    scrollBehavior
                                )
                            }
                        }
                    }

                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Column(
                            modifier = if (scrollBehavior != null) {
                                Modifier
                                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                                    .verticalScroll(rememberScrollState())
                                    .height(1000.dp)
                            } else {
                                Modifier
                            }
                        ) {
                            Row {
                                SelectAppBarSize(
                                    appBarSize = appBarSize,
                                    onSelect = { appBarSize = it })
                            }

                            OutlinedTextField(
                                modifier = Modifier.fillMaxWidth(),
                                value = title,
                                onValueChange = { title = it },
                                label = { Text(text = "title") }
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "navigation")
                                Checkbox(
                                    checked = navigation,
                                    onCheckedChange = { navigation = it })
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "actions:")
                                Slider(value = actions.toFloat(), onValueChange = {
                                    actions = it.toInt()
                                }, valueRange = 0f..9f)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "inset left:")
                                Slider(value = left.toFloat(), onValueChange = {
                                    left = it.toInt()
                                }, valueRange = 0f..100f)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "inset right:")
                                Slider(value = right.toFloat(), onValueChange = {
                                    right = it.toInt()
                                }, valueRange = 0f..100f)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "inset top:")
                                Slider(value = top.toFloat(), onValueChange = {
                                    top = it.toInt()
                                }, valueRange = 0f..100f)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = "inset bottom:")
                                Slider(value = bottom.toFloat(), onValueChange = {
                                    bottom = it.toInt()
                                }, valueRange = 0f..100f)
                            }
                            Column {
                                Row {
                                    Text(text = "none")
                                    Checkbox(
                                        checked = scroll == 0,
                                        onCheckedChange = { scroll = 0 }
                                    )
                                }
                                Row {
                                    Text(text = "enterAlwaysScrollBehavior")
                                    Checkbox(
                                        checked = scroll == 1,
                                        onCheckedChange = { scroll = 1 }
                                    )
                                }
                                Row {
                                    Text(text = "exitUntilCollapsedScrollBehavior")
                                    Checkbox(
                                        checked = scroll == 2,
                                        onCheckedChange = { scroll = 2 }
                                    )
                                }
                                Row {
                                    Text(text = "pinnedScrollBehavior")
                                    Checkbox(
                                        checked = scroll == 3,
                                        onCheckedChange = { scroll = 3 }
                                    )
                                }
                                SelectTheme(themePageState = themePageState)
                                Box {
                                    SelectTopAppBar(topPageState = topPageState)
                                }
                            }
                        }

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

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun SelectTheme(themePageState: PagerState) {
        val height = 50.dp
        Row(Modifier.height(height), verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Theme:")
            VerticalPager(state = themePageState) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = themeTypeList[it].name,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

fun getActions(actions: Int): @Composable () -> Unit = {
    if (actions > 0)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = ""
            )

        }
    if (actions > 1)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = ""
            )
        }
    if (actions > 2)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = ""
            )
        }
    if (actions > 3)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = ""
            )
        }
    if (actions > 4)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = ""
            )
        }
    if (actions > 5)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = ""
            )
        }
    if (actions > 6)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = ""
            )
        }
    if (actions > 7)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = ""
            )
        }
    if (actions > 8)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.ThumbUp,
                contentDescription = ""
            )
        }
}

sealed class AppBarSize @OptIn(ExperimentalMaterial3Api::class) constructor(
    val compose: @Composable (
        title: String,
        navigation: Boolean,
        actions: @Composable RowScope.() -> Unit,
        windowInsets: WindowInsets,
        scrollBehavior: TopAppBarScrollBehavior?
    ) -> Unit
) {
    @OptIn(ExperimentalMaterial3Api::class)
    object CenterAligned : AppBarSize({ title, navigation, actions, windowInsets, scrollBehavior ->
        CenterAlignedTopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                if (navigation)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = ""
                        )
                    }
            },
            actions = actions,
            windowInsets = windowInsets,
            scrollBehavior = scrollBehavior
        )
    })

    @OptIn(ExperimentalMaterial3Api::class)
    object Small : AppBarSize({ title, navigation, actions, windowInsets, scrollBehavior ->
        TopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                if (navigation)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
            }, actions = actions,
            windowInsets = windowInsets,
            scrollBehavior = scrollBehavior
        )
    })

    @OptIn(ExperimentalMaterial3Api::class)
    object Medium : AppBarSize({ title, navigation, actions, windowInsets, scrollBehavior ->
        MediumTopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                if (navigation)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
            }, actions = actions,
            windowInsets = windowInsets,
            scrollBehavior = scrollBehavior
        )
    })

    @OptIn(ExperimentalMaterial3Api::class)
    object Large : AppBarSize({ title, navigation, actions, windowInsets, scrollBehavior ->
        LargeTopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                if (navigation)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
            }, actions = actions, windowInsets = windowInsets,
            scrollBehavior = scrollBehavior
        )
    })
}

@Preview
@Composable
fun test() {
    var appBarSize: AppBarSize by remember { mutableStateOf(AppBarSize.CenterAligned) }
    SelectAppBarSize(appBarSize = appBarSize, onSelect = { appBarSize = it })
}

@Composable
fun SelectAppBarSize(appBarSize: AppBarSize, onSelect: (AppBarSize) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.horizontalScroll(
            rememberScrollState()
        )
    ) {
        Text(text = "Center-aligned")
        Checkbox(
            checked = appBarSize == AppBarSize.CenterAligned,
            onCheckedChange = { onSelect(AppBarSize.CenterAligned) })

        Text(text = "small")
        Checkbox(
            checked = appBarSize == AppBarSize.Small,
            onCheckedChange = { onSelect(AppBarSize.Small) })

        Text(text = "Medium")
        Checkbox(
            checked = appBarSize == AppBarSize.Medium,
            onCheckedChange = { onSelect(AppBarSize.Medium) })

        Text(text = "Large")
        Checkbox(
            checked = appBarSize == AppBarSize.Large,
            onCheckedChange = { onSelect(AppBarSize.Large) })
    }
}


/*
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
}*/
