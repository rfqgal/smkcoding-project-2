package com.example.smkcodingproject2challenge

import androidx.room.*

@Entity(tableName = "identity")
data class ProfileModel(
    var category: String,
    var field: String,
    @PrimaryKey var key: String
)
{
    constructor(): this("","", "")
}