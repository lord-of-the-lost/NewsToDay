package com.example.newstoday.views.mainScreen

import com.example.newstoday.R

data class CardInfo(val title: String, val category: String, val imageId: Int)

val cardList = listOf(
    CardInfo(
        "The latest situation in the presidential election",
        "politics",
        R.drawable._04
    ),
    CardInfo(
        "An updated daily front page",
        "art",
        R.drawable.test_news_img5
    ),
    CardInfo(
        "Text of News #3",
        "sport",
        R.drawable.test_news_img6
    ),
    CardInfo(
        "News #4",
        "life",
        R.drawable.test_news_img1
    ),
)