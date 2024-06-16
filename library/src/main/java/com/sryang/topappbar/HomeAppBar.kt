package com.sryang.topappbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarProvider.HomeAppBar(
    isExpanded: Boolean = false,
    modifier: Modifier = Modifier,
) {
    SearchBar(
        query = "",
        onQueryChange = {},
        placeholder = {
            Text(stringResource(id = R.string.search_for_a_podcast))
        },
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = stringResource(R.string.cd_account)
            )
        },
        modifier = if (isExpanded) modifier else modifier.fillMaxWidth()
    ) { }
}

@Preview
@Composable
fun PreviewHomeAppBar() {
    TopAppBarProvider.HomeAppBar()
}
