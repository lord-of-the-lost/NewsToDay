package com.example.newstoday.views.mainScreen

import com.example.newstoday.R

data class CardInfo(
    val title: String,
    val category: String,
    val imageId: Int,
    var bookmarked: Boolean
)

val cardList = listOf(
    CardInfo(
        "The latest situation in the presidential election",
        "politics",
        R.drawable._04,
        false
    ),
    CardInfo(
        "An updated daily front page",
        "art",
        R.drawable.test_news_img5,
        false
    ),
    CardInfo(
        "Text of News #3",
        "sport",
        R.drawable.test_news_img6,
        false
    ),
    CardInfo(
        "News #4",
        "life",
        R.drawable.test_news_img1,
        false
    ),
    CardInfo(
        "The latest situation in the presidential election",
        "politics",
        R.drawable._02,
        false
    ),
    CardInfo(
        "An updated daily front page",
        "art",
        R.drawable.test_news_img2,
        false
    ),
    CardInfo(
        "Text of News #3",
        "sport",
        R.drawable.test_news_img3,
        false
    ),
    CardInfo(
        "News #4",
        "life",
        R.drawable.test_news_img4,
        false
    ),
)