package com.example.smkcodingproject2challenge.api


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "covid19_indonesia")
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
    val sembuh: String,
    @PrimaryKey
    var key: String
)
/*
{
    constructor(): this("","","","","","")
}
*/