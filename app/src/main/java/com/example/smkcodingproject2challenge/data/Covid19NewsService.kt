package com.example.smkcodingproject2challenge.data

import com.example.smkcodingproject2challenge.api.Covid19NewsIndonesiaArticle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Covid19NewsService {
    @GET("/v2/top-headlines?q=covid-19&country=id&apiKey=0fd4957dbc674991839a37e799305911")
    fun getNewsData(): Call<List<Covid19NewsIndonesiaArticle>>
}