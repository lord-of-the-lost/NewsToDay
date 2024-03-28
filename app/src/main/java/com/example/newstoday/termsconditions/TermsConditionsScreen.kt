package com.example.newstoday.termsconditions

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.newstoday.profile.TopBar
import com.example.newstoday.screen.Screen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
 fun TermsConditionsScreen(navController: NavHostController) {
    Scaffold(topBar = {
        TopBar(
            screen = "Terms & Conditions",
            backToProfile = { navController.navigate(Screen.ProfileScreen.route) })
    }) {
        ScrollableText()
    }

}

@Composable
fun ScrollableText() {
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .padding(top = 120.dp, start = 20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem.",
            style = TextStyle(
                fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp,
                color = Color(0xFF7C82A1)
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )

//        VerticalScrollbar(
//            modifier = Modifier.align(Alignment.CenterEnd),
//            adapter = rememberScrollbarAdapter(scrollState),
//            style = LocalScrollbarStyle.current
//        )
    }
}