package com.example.smkcodingproject2challenge.api


import com.google.gson.annotations.SerializedName

data class Covid19ProvinsiItem(
    @SerializedName("FID")
    val fID: Int,
    @SerializedName("Kasus_Meni")
    val kasusMeninggal: String,
    @SerializedName("Kasus_Posi")
    val kasusPositif: String,
    @SerializedName("Kasus_Semb")
    val kasusSembuh: String,
    @SerializedName("Kode_Provi")
    val kodeProvinsi: String,
    @SerializedName("Provinsi")
    val provinsi: String
)