package com.example.newstoday.views.authorizationScreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newstoday.R
import com.example.newstoday.ui.theme.inter

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        TopScreenText()
        Spacer(modifier = Modifier.height(32.dp))
        EmailField(email, onEmailChange = { email = it }, isError = isEmailError)
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField(password, onPasswordChange = { password = it }, isError = isPasswordError)
        Spacer(modifier = Modifier.height(64.dp))
        SignInButton({/* TODO Написать логику авторизации */})
        Spacer(modifier = Modifier.weight(1f))
        RegisterScreenReference({/* TODO Написать логику переноса на страницу регистрации */})
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun SignInButton(toLogin: () -> Unit) {
    Box(
        modifier = Modifier
            .width(336.dp)
            .height(56.dp)

    ) {
        Button(
            onClick = { toLogin() },
            modifier = Modifier
                .width(336.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF475AD7)),
            shape = RoundedCornerShape(12)
        ) {
            Text(text = stringResource(id = R.string.sign_in))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailField(email: String, onEmailChange: (String) -> Unit, isError: Boolean) {
    var borderColor by remember { mutableStateOf(Color.Gray) }
    var iconTint by remember { mutableStateOf(Color.Gray) }
    Box(
        modifier = Modifier
            .width(336.dp)
            .height(56.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(336.dp)
                .height(56.dp)
                .clip(shape = RoundedCornerShape(12)),
            value = email,
            onValueChange = {
                onEmailChange(it)
                borderColor = if (it.isNotEmpty() && !isError) Color(0xFF475AD7) else Color.Gray
                iconTint = if (it.isNotEmpty() && !isError) Color(0xFF475AD7) else Color.Gray
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.email_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .width(20.dp)
                        .height(16.dp),
                    tint = iconTint
                )
            },
            label = { Text(text = stringResource(id = R.string.email_adress)) },
            isError = isError,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF475AD7),
                cursorColor = Color(0xFF475AD7),
                errorCursorColor = Color(0xFFD74747),
                unfocusedBorderColor = borderColor,
                focusedLabelColor = Color(0xFF475AD7),
                unfocusedLabelColor = if (email.isNotEmpty()) Color(0xFF475AD7) else Color.Gray
            )
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PasswordField(password: String, onPasswordChange: (String) -> Unit, isError: Boolean) {
    var passwordVisibility by remember { mutableStateOf(false) }
    var borderColor by remember { mutableStateOf(Color.Gray) }
    var iconTint by remember { mutableStateOf(Color.Gray) }

    Box(
        modifier = Modifier
            .width(336.dp)
            .height(56.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .width(336.dp)
                .height(56.dp)
                .clip(shape = RoundedCornerShape(12)),
            value = password,
            onValueChange = {
                onPasswordChange(it)
                borderColor = if (it.isNotEmpty() && !isError) Color(0xFF475AD7) else Color.Gray
                iconTint = if (it.isNotEmpty() && !isError) Color(0xFF475AD7) else Color.Gray
            },
            label = { Text(text = stringResource(id = R.string.password)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.password_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .width(16.dp)
                        .height(20.dp),
                    tint = iconTint
                )
            },
            isError = isError,
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        if (passwordVisibility) painterResource(id = R.drawable.eye_open) else painterResource(
                            id = R.drawable.eye_clouse
                        ),
                        contentDescription = "Toggle password visibility",
                        modifier = Modifier
                            .width(20.01.dp)
                            .height(16.dp),
                        tint = iconTint
                    )
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF475AD7),
                cursorColor = Color(0xFF475AD7),
                errorCursorColor = Color(0xFFD74747),
                unfocusedBorderColor = borderColor,
                focusedLabelColor = Color(0xFF475AD7),
                unfocusedLabelColor = if (password.isNotEmpty()) Color(0xFF475AD7) else Color.Gray
            )
        )
    }
}


@Composable
fun TopScreenText() {
    Text(
        modifier = Modifier
            .width(216.dp)
            .height(32.dp),
        text = stringResource(id = R.string.welcome_back),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 32.sp,
            color = Color(0xFF333647),
            fontFamily = inter
        ),
        textAlign = TextAlign.Start,
    )
    Text(
        modifier = Modifier
            .width(336.dp)
            .height(48.dp),
        text = stringResource(id = R.string.welcome_back_description),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            lineHeight = 24.sp,
            color = Color(0xFF7C82A1),
            fontFamily = inter
        ),
        textAlign = TextAlign.Start,
    )
}

@Composable
fun RegisterScreenReference(toRegisterScreen: () -> Unit) {
    ClickableText(
        text = AnnotatedString(stringResource(id = R.string.dont_account)),
        onClick = { offset ->
            if (offset != null) {
                toRegisterScreen()
            }
        },
        modifier = Modifier
            .width(243.dp)
            .height(32.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            lineHeight = 32.sp,
            color = Color(0xFF555A77),
            fontFamily = inter,
            textAlign = TextAlign.Center,
        ),
    )
}


//@Composable
//@Preview(showBackground = true)
//fun LoginScreenPreview() {
//    LoginScreen()
//}