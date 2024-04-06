package com.example.newstoday.core

import com.example.newstoday.core.network.Article
import com.example.newstoday.core.storage.ArticleEntity

class ArticleUseCases {
    companion object {
        fun filterRemovedArticles(articles: List<Article>): List<Article> {
            return articles.filterNot { article ->
                article.url == "https://removed.com"
            }
        }

        fun mapArticleToArticleModel(article: Article): ArticleModel {
            return ArticleModel(
                id = article.url,
                title = article.title,
                content = article.content ?: "No data",
                urlToImage = article.urlToImage
                    ?: "https://blamper.ru/steady/55/40/b8/original/5540b891fa1ad9e3148b45db/400x200.jpg",
                author = article.author ?: "Unknown",
                tag = article.source.name,
                isBookmarked = false
            )
        }

        fun mapArticleModelToArticleEntity(articleModel: ArticleModel): ArticleEntity {
            return ArticleEntity(
                id = articleModel.id,
                author = articleModel.author,
                title = articleModel.title,
                content = articleModel.content,
                imageData = null
            )
        }
    }
}