package com.example.smkcodingproject2challenge.api


import com.google.gson.annotations.SerializedName

data class Covid19NewsIndonesiaSource(
    @SerializedName("id")
    val id: Any,
    @SerializedName("name")
    val name: String
)