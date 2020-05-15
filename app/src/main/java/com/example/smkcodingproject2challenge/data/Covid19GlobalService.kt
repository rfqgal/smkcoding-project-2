package com.example.smkcodingproject2challenge.data

import com.example.smkcodingproject2challenge.api.Covid19GlobalItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface Covid19GlobalService {
    @GET()
    fun getGlobalData(@Url url: String): Call<List<Covid19GlobalItem>>
}