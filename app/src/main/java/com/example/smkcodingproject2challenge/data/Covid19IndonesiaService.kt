package com.example.smkcodingproject2challenge.data

import com.example.smkcodingproject2challenge.api.Covid19IndonesiaItem
import retrofit2.Call
import retrofit2.http.GET

interface Covid19IndonesiaService {
    @GET("/indonesia")
    fun getIndonesiaData(): Call<List<Covid19IndonesiaItem>>
}