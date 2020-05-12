package com.example.smkcodingproject2challenge.api


import com.google.gson.annotations.SerializedName

data class Covid19ProvinsiItem(
    @SerializedName("FID")
    val fID: Int,
    @SerializedName("Kasus_Meni")
    val kasusMeninggal: Int,
    @SerializedName("Kasus_Posi")
    val kasusPositif: Int,
    @SerializedName("Kasus_Semb")
    val kasusSembuh: Int,
    @SerializedName("Kode_Provi")
    val kodeProvinsi: Int,
    @SerializedName("Provinsi")
    val provinsi: String
)