package com.example.newstoday.core

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newstoday.core.network.NewsResponse
import com.example.newstoday.core.network.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class NewsViewModel : ViewModel() {
    private val apiService = RetrofitClient.create()
    private val repository = NewsRepository(apiService)
    var newsResponse = mutableStateOf<NewsResponse?>(null)
    var errorMessage = mutableStateOf<String?>(null)

    init {
        getTopHeadlines()
    }

    private fun makeApiCall(call: Call<NewsResponse>) {
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    newsResponse.value = response.body()
                } else {
                    errorMessage.value = "Произошла ошибка: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                errorMessage.value = "Ошибка сети: ${t.message}"
            }
        })
    }

    fun getEverything(query: String) {
        val apiCall = repository.getEverything(query)
        makeApiCall(apiCall)
    }

    fun getTopHeadlines(country: String = "us") {
        val apiKey = "9eb135314d134f44b60d88e096de26f6"
        val apiCall = repository.getTopHeadlines(country, apiKey)
        makeApiCall(apiCall)
    }
}
