package com.example.smkcodingproject2challenge.data

import com.example.smkcodingproject2challenge.api.Covid19NewsIndonesiaArticle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Covid19NewsService {
    @GET()
    fun getNewsData(url: String): Call<List<Covid19NewsIndonesiaArticle>>
}