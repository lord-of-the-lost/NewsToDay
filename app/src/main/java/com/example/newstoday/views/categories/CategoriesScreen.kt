package com.example.newstoday.views.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newstoday.R
import com.example.newstoday.core.NewsViewModel
import com.example.newstoday.navigation.Screen
import com.example.newstoday.ui.theme.inter

@Composable
fun CategoriesScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: NewsViewModel
) {
    val initialSetupCompleted = viewModel.initialCategorySetupCompleted.value
    val categoriesList = categoriesList()
    Column(
        modifier = modifier
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(categoriesList) { item ->

                var isSelectedCategory by remember {
                    mutableStateOf(false)
                }

                Box(
                    modifier = Modifier
                        .height(72.dp)
                        .clip(shape = RoundedCornerShape(12.dp))
                        .clickable {
                            isSelectedCategory = !isSelectedCategory
                            item.selected = isSelectedCategory
                        }
                        .background(
                            Color(
                                if (isSelectedCategory)
                                    0xFF475AD7
                                else
                                    0xFFF3F4F6
                            )
                        ),

                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = item.name,
                        fontFamily = inter,
                        fontWeight = FontWeight(600),
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color(
                            if (isSelectedCategory)
                                0xFFFFFFFF
                            else
                                0xFF666C8E
                        )
                    )
                }
            }
        }

        if (!initialSetupCompleted) {
            Button(
                onClick = {
                    viewModel.initialCategorySetupCompleted.value = true
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF475AD7)),
                elevation = ButtonDefaults.elevation(defaultElevation = 0.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.buttonTexts_next),
                    color = Color.White,
                    fontFamily = inter,
                    lineHeight = 24.sp,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                )
            }
        }
    }
}

//@Preview (showBackground = true)
//@Composable
//fun CategoriesPreview(){
//    Categories()
//}