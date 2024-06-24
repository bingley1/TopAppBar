package com.sryang.topappbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

object TopAppBarProvider

sealed class TopAppBarTypes(val name: String, val topBar: @Composable (() -> Unit)?) {
    object None : TopAppBarTypes("None", null)
    object Home : TopAppBarTypes("Home", { TopAppBarProvider.HomeAppBar() })
    object Facebook : TopAppBarTypes("Facebook", { TopAppBarProvider.FaceBookTopAppBar() })
    object Simple : TopAppBarTypes("Simple", { TopAppBarProvider.SimpleTopAppBar() })

    @OptIn(ExperimentalMaterial3Api::class)
    object Youtube : TopAppBarTypes("Youtube", { TopAppBarProvider.YoutubeTopAppBar() })
    object Survey : TopAppBarTypes("Survey", { TopAppBarProvider.SurveyTopAppBar() })

    @OptIn(ExperimentalMaterial3Api::class)
    object ChannelName : TopAppBarTypes("ChannelName", { TopAppBarProvider.ChannelNameBar() })
}

val topAppBarTypeList = listOf(
    TopAppBarTypes.None,
    TopAppBarTypes.Home,
    TopAppBarTypes.Facebook,
    TopAppBarTypes.Simple,
    TopAppBarTypes.Youtube,
    TopAppBarTypes.Survey,
    TopAppBarTypes.ChannelName
)