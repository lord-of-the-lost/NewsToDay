package com.example.newstoday.views.categories

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.newstoday.R

data class Categories(val id: Int, val name: String, var selected: Boolean)

@Composable
fun categoriesList(): List<Categories> {
    return listOf(
        Categories(
            0,
            stringResource(id = R.string.sports),
            false,
        ),
        Categories(
            1,
            stringResource(id = R.string.politics),
            false,
        ),
        Categories(
            2,
            stringResource(id = R.string.life),
            false,
        ),
        Categories(
            3,
            stringResource(id = R.string.gaming),
            false,
        ),
        Categories(
            4,
            stringResource(id = R.string.animals),
            false,
        ),
        Categories(
            5,
            stringResource(id = R.string.nature),
            false,
        ),
        Categories(
            6,
            stringResource(id = R.string.food),
            false,
        ),
        Categories(
            7,
            stringResource(id = R.string.art),
            false,
        ),
        Categories(
            8,
            stringResource(id = R.string.history),
            false,
        ),
        Categories(
            9,
            stringResource(id = R.string.fashion),
            false,
        ),
    )
}