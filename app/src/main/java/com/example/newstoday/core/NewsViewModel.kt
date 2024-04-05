package com.example.newstoday.core

import android.app.Application
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.MutableState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.newstoday.core.network.Article
import com.example.newstoday.core.network.RetrofitClient
import com.example.newstoday.core.storage.ArticleDao
import com.example.newstoday.core.storage.ArticleDatabase
import com.example.newstoday.core.storage.ArticleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
class NewsViewModel(application: Application) : AndroidViewModel(application) {
    private val imageLoader = ImageLoader(application)
    private val apiService = RetrofitClient.create()
    private val apiKey = "9eb135314d134f44b60d88e096de26f6"
    private val articleDao: ArticleDao by lazy {
        Room.databaseBuilder(
            application,
            ArticleDatabase::class.java, "database.db"
        ).build().articleDao()
    }

    private val repository: NewsRepository by lazy {
        NewsRepository(apiService, articleDao)
    }
    var initialCategorySetupCompleted = mutableStateOf(false)
    var recomendedNewsResponse: MutableState<List<ArticleModel>?> = mutableStateOf(null)
    var bigItemsResponse: MutableState<List<ArticleModel>?> = mutableStateOf(null)
    var errorMessage: MutableState<String?> = mutableStateOf(null)

    init {
        loadTopHeadlines()
    }

    fun loadTopHeadlines(country: String = "us") {
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines(country, apiKey)
                response?.let {
                    bigItemsResponse.value = it.map { article ->
                        ArticleUseCases.mapArticleToArticleModel(article)
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = "Не удалось загрузить заголовки новостей: ${e.message}"
            }
        }
    }

    fun loadEverything(query: String) {
        viewModelScope.launch {
            try {
                val response = repository.getEverything(query, apiKey)
                response?.let {
                    bigItemsResponse.value = it.map(ArticleUseCases::mapArticleToArticleModel)
                }
            } catch (e: Exception) {
                errorMessage.value = "Не удалось загрузить все новости: ${e.message}"
            }
        }
    }

    fun loadArticlesByCategories(categories: List<String>) {
        viewModelScope.launch {
            val results = mutableListOf<ArticleModel>()
            categories.forEach { category ->
                try {
                    val articles = repository.getEverything(category, apiKey)
                    articles?.let {
                        results.addAll(it.map(ArticleUseCases::mapArticleToArticleModel))
                    }
                } catch (e: Exception) {
                    errorMessage.value = "Ошибка при загрузке статей по категориям: ${e.message}"
                }
            }
            recomendedNewsResponse.value = results.shuffled()
        }
    }

    fun saveArticle(articleModel: ArticleModel) {
        viewModelScope.launch {
            try {
                val imageData = downloadImage(articleModel.urlToImage)
                val articleEntity = ArticleUseCases.mapArticleModelToArticleEntity(articleModel).copy(imageData = imageData)
                repository.saveArticle(articleEntity)
            } catch (e: Exception) {
                errorMessage.value = "Не удалось сохранить статью: ${e.message}"
            }
        }
    }

    private suspend fun downloadImage(urlToImage: String?): ByteArray? {
        return withContext(Dispatchers.IO) { // withContext переключает выполнение в фоновый поток
            try {
                urlToImage?.let { imageUrl ->
                    val imageRequest =
                        ImageRequest.Builder(getApplication<Application>()) // Создаем запрос на изображение
                            .data(imageUrl) // Устанавливаем URL как данные для запроса
                            .allowHardware(false) // Отключаем аппаратные битмапы для совместимости с .toBitmap()
                            .build() // Построение запроса
                    val result =
                        imageLoader.execute(imageRequest) // Выполнение запроса и сохранение результата
                    if (result is SuccessResult) {
                        val bitmap =
                            (result.drawable as BitmapDrawable).bitmap  // Преобразование Drawable в Bitmap для получения массива байтов
                        val stream =
                            ByteArrayOutputStream() // Используем ByteArrayOutputStream для записи битов битмапа
                        bitmap.compress(
                            Bitmap.CompressFormat.PNG,
                            100,
                            stream
                        )  // Сжимаем битмап в формат PNG с максимальным качеством
                        stream.toByteArray()  // Преобразуем поток в массив байтов и возвращаем его
                    } else {
                        null
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
