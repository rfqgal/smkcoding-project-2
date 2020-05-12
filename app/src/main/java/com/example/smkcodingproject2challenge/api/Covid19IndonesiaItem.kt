package com.example.smkcodingproject2challenge.api


import com.google.gson.annotations.SerializedName

data class Covid19IndonesiaItem(
    @SerializedName("dirawat")
    val dirawat: String,
    @SerializedName("meninggal")
    val meninggal: String,
    @SerializedName("name")
    val negara: String,
    @SerializedName("positif")
    val positif: String,
    @SerializedName("sembuh")
    val sembuh: String
)