package com.example.newstoday.views.mainScreen.recommended

data class RecommendedNewsArticle(
    val title: String,
    val category: String,
    val urlToImage: String?,
)


val createSampleNewsArticles = listOf(
    RecommendedNewsArticle(
        title = "Solana Leads Way As Most Big Cryptocurrencies Post Gains",
        category = "Politics",
        urlToImage = "https://www.marketscreener.com/images/twitter_MS_fdblanc.png",
    ),
    RecommendedNewsArticle(
        title = "Tesla's Tactical Change May Deliver Fewer Vehicles But Could Raise Profitability: Analyst",
        category = "Art",
        urlToImage = "https://c.biztoc.com/p/575a8ed47d52d396/s.webp",
    ),
    RecommendedNewsArticle(
        title = "Tesla offers a CyberHammer to people who help sell its cars",
        category = "Life",
        urlToImage = "https://c.biztoc.com/p/145525bf59633574/s.webp",
    ),
    RecommendedNewsArticle(
        title = "US DOJ Gives Us Another Reason Automakers Might Be Ditching Apple CarPlay",
        category = "Sports",
        urlToImage = "https://cleantechnica.com/wp-content/uploads/2024/03/Apple-Vision-Pro-Press-Photo.png",
    ),
    RecommendedNewsArticle(
        title = "Solana Leads Way As Most Big Cryptocurrencies Post Gains",
        category = "Politics",
        urlToImage = "https://www.marketscreener.com/images/twitter_MS_fdblanc.png",
    ),
    RecommendedNewsArticle(
        title = "Tesla's Tactical Change May Deliver Fewer Vehicles But Could Raise Profitability: Analyst",
        category = "Art",
        urlToImage = "https://c.biztoc.com/p/575a8ed47d52d396/s.webp",
    ),
    RecommendedNewsArticle(
        title = "Tesla offers a CyberHammer to people who help sell its cars",
        category = "Life",
        urlToImage = "https://c.biztoc.com/p/145525bf59633574/s.webp",
    ),
    RecommendedNewsArticle(
        title = "US DOJ Gives Us Another Reason Automakers Might Be Ditching Apple CarPlay",
        category = "Sports",
        urlToImage = "https://cleantechnica.com/wp-content/uploads/2024/03/Apple-Vision-Pro-Press-Photo.png",
    ),
)