package com.example.smkcodingproject2challenge.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.smkcodingproject2challenge.api.Covid19IndonesiaItem

@Dao
interface Covid19IndonesiaDao {
    @Query("SELECT * FROM covid19_indonesia")
    fun getIndonesiaItem(): LiveData<List<Covid19IndonesiaItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(indonesiaItem: Covid19IndonesiaItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(indonesiaItemAll: List<Covid19IndonesiaItem>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(indonesiaItem: Covid19IndonesiaItem)

    @Delete()
    suspend fun delete(indonesiaItem: Covid19IndonesiaItem)

    @Query("DELETE FROM covid19_indonesia")
    suspend fun deleteAll()
}