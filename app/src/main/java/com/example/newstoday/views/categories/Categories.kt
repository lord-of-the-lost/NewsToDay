package com.example.newstoday.views.categories

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.newstoday.R

data class Categories(val name: String, var selected: Boolean)

@Composable
fun categoriesList(): List<Categories> {
	return listOf(
		Categories(
			stringResource(id = R.string.sports),
			false,
		),
		Categories(
			stringResource(id = R.string.politics),
			false,
		),
		Categories(
			stringResource(id = R.string.life),
			false,
		),
		Categories(
			stringResource(id = R.string.gaming),
			false,
		),
		Categories(
			stringResource(id = R.string.animals),
			false,
		),
		Categories(
			stringResource(id = R.string.nature),
			false,
		),
		Categories(
			stringResource(id = R.string.food),
			false,
		),
		Categories(
			stringResource(id = R.string.art),
			false,
		),
		Categories(
			stringResource(id = R.string.history),
			false,
		),
		Categories(
			stringResource(id = R.string.fashion),
			false,
		),
	)
}