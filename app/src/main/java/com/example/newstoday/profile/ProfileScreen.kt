package com.example.newstoday.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.newstoday.R
import com.example.newstoday.UserData

@Composable
fun ProfileScreen(
    toLanguageScreen: () -> Unit,
    toTermsConditionsScreen: () -> Unit,
    signOut: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(top = 72.dp, start = 20.dp)
            .width(201.dp)
            .height(136.dp)
    ) {
        Column {
            Text(
                text = "Profile",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W600,
                    lineHeight = 24.sp,
                    color = Color.Black
                ),
                textAlign = TextAlign.Start
            )
            Row {
                ProfileImage(url = "https://us.123rf.com/450wm/happyvector071/happyvector0711904/happyvector071190414500/120957417-creative-illustration-of-default-avatar-profile-placeholder-isolated-on-background-art-design-grey.jpg")
                val s = UserData("Test", "Test@email.ru")
                ProfileInfo(userData = s)

            }
        }
    }
    LanguageButton({ toLanguageScreen() })
    TermsConditionsButton({ toTermsConditionsScreen() })
    SignOutButton({ signOut() })
}

@Composable
fun LanguageButton(toLanguageScreen: () -> Unit) {
    Box(
        modifier = Modifier
            .width(336.dp)
            .height(56.dp)
            .padding(top = 252.dp, start = 20.dp)
    ) {
        Button(
            onClick = { toLanguageScreen() },
            modifier = Modifier
                .width(336.dp)
                .height(56.dp)
                .fillMaxWidth(),
            shape = CutCornerShape(12),
            colors = ButtonDefaults.buttonColors(Color(0xFFF3F4F6))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Language",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        lineHeight = 24.sp,
                        color = Color(0xFFF666C8E)
                    ),
                    textAlign = TextAlign.Start,

                    )
                Image(
                    painter = painterResource(id = R.drawable.vector_button),
                    contentDescription = null,
                    modifier = Modifier
                        .width(6.25.dp)
                        .height(10.49.dp),
                    alignment = Alignment.Center
                )
            }

        }
    }
}

@Composable
fun TermsConditionsButton(toTermsConditionsScreen: () -> Unit) {
    Box(
        modifier = Modifier
            .width(336.dp)
            .height(56.dp)
            .padding(top = 548.dp, start = 20.dp)
    ) {
        Button(
            onClick = { toTermsConditionsScreen() },
            modifier = Modifier
                .width(336.dp)
                .height(56.dp)
                .fillMaxWidth(),
            shape = CutCornerShape(12),
            colors = ButtonDefaults.buttonColors(Color(0xFFF3F4F6))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Terms & Conditions",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        lineHeight = 24.sp,
                        color = Color(0xFFF666C8E)
                    ),
                    textAlign = TextAlign.Start,

                    )
                Image(
                    painter = painterResource(id = R.drawable.vector_button),
                    contentDescription = null,
                    modifier = Modifier
                        .width(6.25.dp)
                        .height(10.49.dp),
                    alignment = Alignment.Center
                )
            }

        }
    }
}

@Composable
fun ProfileImage(url: String) {
    val painter = rememberImagePainter(data = url)
    Box(
        modifier = Modifier
            .size(72.dp)
            .padding(top = 4.dp)
            .clip(RoundedCornerShape(100)),
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            painter = painter,
            contentDescription = "profile picture",
            Modifier
                .width(72.dp)
                .height(72.dp)
        )
    }
}

@Composable
fun SignOutButton(signOut: () -> Unit) {
    Box(
        modifier = Modifier
            .width(336.dp)
            .height(56.dp)
            .padding(top = 632.dp, start = 20.dp)
    ) {
        Button(
            onClick = { signOut() },
            modifier = Modifier
                .width(336.dp)
                .height(56.dp)
                .fillMaxWidth(),
            shape = CutCornerShape(12),
            colors = ButtonDefaults.buttonColors(Color(0xFFF3F4F6))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Sign Out",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W600,
                        lineHeight = 24.sp,
                        color = Color(0xFFF666C8E)
                    ),
                    textAlign = TextAlign.Start,

                    )
                Image(
                    painter = painterResource(id = R.drawable.sign_out),
                    contentDescription = null,
                    modifier = Modifier
                        .width(16.dp)
                        .height(20.dp),
                    alignment = Alignment.Center
                )
            }

        }
    }
}

@Composable
fun ProfileInfo(userData: UserData) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
    ) {

        Text(
            text = userData.name,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                lineHeight = 24.sp,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )
        Text(
            text = userData.email,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 24.sp,
                color = Color.LightGray
            ),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen({}, {}, {})
}