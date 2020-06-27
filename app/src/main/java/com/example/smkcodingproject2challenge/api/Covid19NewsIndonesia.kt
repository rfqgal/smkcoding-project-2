package com.example.smkcodingproject2challenge.api


import com.google.gson.annotations.SerializedName

data class Covid19NewsIndonesia(
    @SerializedName("articles")
    val articles: List<Covid19NewsIndonesiaArticle>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)