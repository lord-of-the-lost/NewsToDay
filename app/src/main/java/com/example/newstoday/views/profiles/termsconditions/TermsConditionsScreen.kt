package com.example.newstoday.views.profiles.termsconditions


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newstoday.R
import com.example.newstoday.core.NewsViewModel
import com.example.newstoday.ui.theme.inter

@Composable
fun ScrollableText(
    modifier: Modifier,
    navController: NavController,
    viewModel: NewsViewModel
) {
    Text(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        text = stringResource(id = R.string.terms_conditions_text),
        style = TextStyle(
            fontFamily = inter,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            lineHeight = 24.sp,
            color = Color(0xFF7C82A1)
        ),
    )
}
