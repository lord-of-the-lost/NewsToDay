package com.example.newstoday.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newstoday.R

@Composable
 fun TopBar(screen: String, backToProfile: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(top = 68.dp, start = 20.dp)
            .width(281.dp)
            .height(32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "back to profile",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { backToProfile() }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = screen,
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W600,
                    lineHeight = 32.sp,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TopBarPreview(){
    TopBar("Screen", {})
}