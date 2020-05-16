package com.example.smkcodingproject2challenge.data

import com.example.smkcodingproject2challenge.api.Covid19ProvinsiItem
import retrofit2.Call
import retrofit2.http.GET

interface Covid19ProvinsiService {
    @GET("/indonesia/provinsi")
    fun getProvinsiData(): Call<List<Covid19ProvinsiItem>>
}