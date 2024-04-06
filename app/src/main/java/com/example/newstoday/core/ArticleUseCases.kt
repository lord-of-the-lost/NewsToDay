package com.example.newstoday.core

import com.example.newstoday.core.network.Article
import com.example.newstoday.core.storage.ArticleEntity

class ArticleUseCases {
    companion object {
        fun filterRemovedArticles(articles: List<Article>): List<Article> {
            return articles.filterNot { article ->
                article.url == "https://removed.com"
                        || article.description == null
                        || article.author == null
                        || article.urlToImage == null
                        || article.author?.startsWith("http") == true
            }
        }

        fun mapArticleToArticleModel(article: Article): ArticleModel {
            return ArticleModel(
                id = article.url,
                title = article.title,
                content = article.description ?: "No data",
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
                tag = articleModel.tag,
                urlToImage = articleModel.urlToImage
            )
        }

        fun mapArticleEntityToArticleModel(articleEntity: ArticleEntity): ArticleModel {
            return ArticleModel(
                id = articleEntity.id,
                title = articleEntity.title,
                content = articleEntity.content,
                urlToImage = articleEntity.urlToImage,
                author = articleEntity.author,
                tag = articleEntity.tag,
                isBookmarked = true
            )
        }
    }
}