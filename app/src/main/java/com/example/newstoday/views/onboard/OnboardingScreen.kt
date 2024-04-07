package com.example.newstoday.views.onboard

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newstoday.R
import com.example.newstoday.navigation.Screen
import com.example.newstoday.ui.theme.inter
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(modifier: Modifier, navController: NavController) {
    val listOfImages = listOf(
        R.drawable._01,
        R.drawable._02,
        R.drawable._03
    )

    val pagerState = rememberPagerState(pageCount = { listOfImages.size })
    val pagerScope = rememberCoroutineScope()

    var firstKnowTextIndex by remember { mutableIntStateOf(0) }
    val firstKnowTexts = listOf(
        stringResource(id = R.string.first_to_know),
        "",
        ""
    )

    var descriptionAppIndex by remember { mutableIntStateOf(0) }
    val descriptionApp = listOf(
        stringResource(id = R.string.descriptionApp_1),
        stringResource(id = R.string.description_App_2),
        stringResource(id = R.string.description_App_3)
    )

    var buttonTextIndex by remember { mutableIntStateOf(0) }
    val buttonTexts = listOf(
        stringResource(id = R.string.buttonTexts_next),
        stringResource(id = R.string.buttonTexts_next),
        stringResource(id = R.string.buttonTexts_GetStarted)
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier
                .padding(top = 96.dp),
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 55.dp),
        ) { index ->

            val pageOffSet =
                (pagerState.currentPage - index) + pagerState.currentPageOffsetFraction
            val imageSize by animateFloatAsState(
                targetValue = if (pageOffSet != 0.0f) 0.85714f else 1f,
                animationSpec = tween(durationMillis = 300), label = ""
            )
            Image(
                modifier = Modifier
                    .size(288.dp, 336.dp)
                    .graphicsLayer {
                        scaleX = imageSize
                        scaleY = imageSize
                    }
                    .clip(RoundedCornerShape(12.dp)),
                painter = painterResource(id = listOfImages[index]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            if (pagerState.currentPage == index) {
                buttonTextIndex = index
                firstKnowTextIndex = index
                descriptionAppIndex = index
            }
        }
        //Отображает точки страниц внизу
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color(0xFF475AD7) else Color(0xFFF3F4F6)
                val x =
                    if (pagerState.currentPage == iteration) 24.dp else 8.dp
                Spacer(modifier = Modifier.width(4.dp))
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(color)
                        .size(x, 8.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
            }
        }

        Text(
            modifier = Modifier
                .padding(top = 34.dp),
            text = firstKnowTexts[firstKnowTextIndex],
            fontFamily = inter,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF333647),
        )

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(top = 24.dp)
                .size(216.dp, 72.dp)
        ) {
            Text(
                text = descriptionApp[descriptionAppIndex],
                fontFamily = inter,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color(0xFF7C82A1),
            )
        }

        Button(
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    top = 40.dp,
                    end = 20.dp
                )
                .height(56.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults
                .buttonColors(
                    containerColor = Color(0xFF475AD7),
                    contentColor = Color.White
                ),
            onClick = {
                pagerScope.launch { pagerState.scrollToPage(pagerState.currentPage + 1) }
                if (buttonTexts[buttonTextIndex] == "Get Started" || buttonTexts[buttonTextIndex] == "Начать") {
                    navController.navigate(Screen.Authorization.route)
                }
            }) {
            Text(
                text = buttonTexts[buttonTextIndex],
                fontFamily = inter,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}