package com.example.smkcodingproject2challenge.api


import com.google.gson.annotations.SerializedName

data class Covid19GlobalItem(
    @SerializedName("attributes")
    val attributes: Covid19GlobalAttributes
)