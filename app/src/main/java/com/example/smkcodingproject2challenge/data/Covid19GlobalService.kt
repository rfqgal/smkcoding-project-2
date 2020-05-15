package com.example.smkcodingproject2challenge.data

import com.example.smkcodingproject2challenge.api.Covid19GlobalItem
import retrofit2.Call
import retrofit2.http.GET

interface Covid19GlobalService {
    @GET("")
    fun getGlobalData(): Call<List<Covid19GlobalItem>>
}