package com.example.newstoday.core

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.newstoday.core.network.RetrofitClient
import com.example.newstoday.core.storage.ArticleDao
import com.example.newstoday.core.storage.ArticleDatabase
import com.example.newstoday.views.categories.Categories
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {
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
    var selectedArticle = mutableStateOf<ArticleModel?>(null)
    var recommendedNewsResponse: MutableState<List<ArticleModel>?> = mutableStateOf(null)
    var bigItemsResponse: MutableState<List<ArticleModel>?> = mutableStateOf(null)
    var savedArticles: MutableState<List<ArticleModel>?> = mutableStateOf(null)
    var errorMessage: MutableState<String?> = mutableStateOf(null)
    var categories = mutableStateListOf<Categories>()

    fun initializeCategories(newCategoriesList: List<Categories>) {
        if (categories.isEmpty()) {
            categories.addAll(newCategoriesList)
        } else {
            val selectedStates = categories.associate { it.id to it.selected }
            categories.clear()
            categories.addAll(newCategoriesList.map { newCategory ->
                newCategory.copy(selected = selectedStates[newCategory.id] ?: false)
            })
        }
    }

    fun toggleCategorySelected(index: Int) {
        val category = categories[index]
        categories[index] = category.copy(selected = !category.selected)
    }

    fun loadTopHeadlines(country: String = "us") {
        viewModelScope.launch {
            try {
                val response = repository.getTopHeadlines(country, apiKey)
                val savedArticleIds = getSavedArticlesIds() // Получаем сохраненные идентификаторы
                response?.let {
                    val filteredArticles = ArticleUseCases.filterRemovedArticles(it)
                    val articleModels = filteredArticles.map { article ->
                        ArticleUseCases.mapArticleToArticleModel(article).apply {
                            isBookmarked = id in savedArticleIds
                        }
                    }
                    bigItemsResponse.value = articleModels
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
                val savedArticleIds = getSavedArticlesIds()
                response?.let {
                    val filteredArticles = ArticleUseCases.filterRemovedArticles(it)
                    val articleModels = filteredArticles.map { article ->
                        ArticleUseCases.mapArticleToArticleModel(article).apply {
                            isBookmarked = id in savedArticleIds
                        }
                    }
                    bigItemsResponse.value = articleModels
                }
            } catch (e: Exception) {
                errorMessage.value = "Не удалось загрузить все новости: ${e.message}"
            }
        }
    }

    fun loadArticlesBySelectedCategories() {
        val selectedCategoriesNames = categories.filter { it.selected }.map { it.name }
        loadArticlesByCategories(selectedCategoriesNames)
    }

    fun deleteArticle(articleModel: ArticleModel) {
        viewModelScope.launch {
            try {
                val articleEntity = ArticleUseCases.mapArticleModelToArticleEntity(articleModel)
                repository.deleteArticle(articleEntity)
            } catch (e: Exception) {
                errorMessage.value = "Не удалось удалить статью: ${e.message}"
            }
        }
    }

    fun saveArticle(articleModel: ArticleModel) {
        viewModelScope.launch {
            try {
                val articleEntity = ArticleUseCases.mapArticleModelToArticleEntity(articleModel)
                repository.saveArticle(articleEntity)
            } catch (e: Exception) {
                errorMessage.value = "Не удалось сохранить статью: ${e.message}"
            }
        }
    }

    fun getSavedArticles() {
        viewModelScope.launch {
            try {
                val articleEntities = repository.getSavedArticles()
                val articleModels = articleEntities.map { entity ->
                    ArticleUseCases.mapArticleEntityToArticleModel(entity).copy(isBookmarked = true)
                }
                savedArticles.value = articleModels
            } catch (e: Exception) {
                errorMessage.value = "Не удалось получить сохраненные статьи: ${e.message}"
            }
        }
    }

    fun toggleBookmark(articleModel: ArticleModel) {
        viewModelScope.launch {
            val newBookmarkState = !articleModel.isBookmarked
            val updatedArticle = articleModel.copy(isBookmarked = newBookmarkState)

            if (newBookmarkState) {
                saveArticle(updatedArticle)
            } else {
                deleteArticle(updatedArticle)
            }

            bigItemsResponse.value = bigItemsResponse.value?.map { if (it.id == articleModel.id) updatedArticle else it }
            recommendedNewsResponse.value = recommendedNewsResponse.value?.map { if (it.id == articleModel.id) updatedArticle else it }
        }
    }

    private suspend fun getSavedArticlesIds(): Set<String> {
        val savedArticles = repository.getSavedArticles()
        return savedArticles.map { it.id }.toSet()
    }

    private fun loadArticlesByCategories(categoriesNames: List<String>) {
        viewModelScope.launch {
            val results = mutableListOf<ArticleModel>()
            val savedArticleIds = getSavedArticlesIds()
            categoriesNames.forEach { categoryName ->
                try {
                    val articles = repository.getEverything(categoryName, apiKey)
                    articles?.let {
                        val filteredArticles = ArticleUseCases.filterRemovedArticles(it)
                        val articleModels = filteredArticles.map { article ->
                            ArticleUseCases.mapArticleToArticleModel(article).apply {
                                isBookmarked = id in savedArticleIds
                            }
                        }
                        results.addAll(articleModels)
                    }
                } catch (e: Exception) {
                    errorMessage.value = "Ошибка при загрузке статей по категории '$categoryName': ${e.message}"
                }
            }
            recommendedNewsResponse.value = results.shuffled()
        }
    }
}
