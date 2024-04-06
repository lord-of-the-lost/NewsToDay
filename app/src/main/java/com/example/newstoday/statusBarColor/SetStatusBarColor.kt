package com.example.newstoday.statusBarColor

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

@RequiresApi(Build.VERSION_CODES.R)
@SuppressLint("ComposableNaming")
@Composable
fun SetStatusBarColor(color: Color) {
	
	val view =
		LocalView.current //получаем доступ к текущему представлению (view) внутри Composable-функции
	
	if (!view.isInEditMode) { //проверяет, что код выполняется не во время редактирования макета (Edit Mode)
		LaunchedEffect(key1 = true) {//запускает блок кода асинхронно, когда изменяется указанный ключ (в данном случае, это true)
			val window =
				(view.context as Activity).window //способ получить доступ к объекту Window текущей активности
			
			window.statusBarColor =
				color.toArgb() //устанавливается цвет статусного бара, используя color.toArgb(), чтобы преобразовать Color в формат ARGB (Alpha, Red, Green, Blue)
			
			// Получаем контроллер для управления отступами
			val controller = window.insetsController
			
			if (controller != null) {
				if (color == Color.White) {
					// Делаем текст и иконки статусбара светлыми
					controller.setSystemBarsAppearance(
						WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
						WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
					)
				} else {
					// Делаем текст и иконки статусбара темными
					controller.setSystemBarsAppearance(
						0,
						WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
					)
				}
			}
		}
	}
}