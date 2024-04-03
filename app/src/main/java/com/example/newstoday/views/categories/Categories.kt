package com.example.newstoday.views.categories

data class Categories(val name: String, var selected: Boolean)


val categoriesList = listOf(
    Categories(
        "\uD83C\uDFC8   Sports",
        false,
    ),
    Categories(
        "⚖\uFE0F   Politics",
        false,
    ),
    Categories(
        "\uD83C\uDF1E   Life",
        false,
    ),
    Categories(
        "\uD83C\uDFAE   Gaming",
        false,
    ),
    Categories(
        "\uD83D\uDC3B   Animals",
        false,
    ),
    Categories(
        "\uD83C\uDF34   Nature",
        false,
    ),
    Categories(
        "\uD83C\uDF54   Food",
        false,
    ),
    Categories(
        "\uD83C\uDFA8   Art",
        false,
    ),
    Categories(
        "\uD83D\uDCDC   History",
        false,
    ),
    Categories(
        "\uD83D\uDC57   Fashion",
        false,
    ),
)
