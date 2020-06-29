package com.example.smkcodingproject2challenge.repos

import androidx.lifecycle.LiveData
import com.example.smkcodingproject2challenge.api.Covid19IndonesiaItem
import com.example.smkcodingproject2challenge.dao.Covid19IndonesiaDao

class Covid19IndonesiaRepository(private val covid19IndonesiaDao: Covid19IndonesiaDao) {

    val allIndonesiaItem: LiveData<List<Covid19IndonesiaItem>> = covid19IndonesiaDao.getIndonesiaItem()

    suspend fun insert(indonesiaItem: Covid19IndonesiaItem) {
        covid19IndonesiaDao.insert(indonesiaItem)
    }

    suspend fun insertAll(indonesiaItems: List<Covid19IndonesiaItem>) {
        covid19IndonesiaDao.insertAll(indonesiaItems)
    }

    suspend fun delete(indonesiaItem: Covid19IndonesiaItem) {
        covid19IndonesiaDao.delete(indonesiaItem)
    }

    suspend fun deleteAll() {
        covid19IndonesiaDao.deleteAll()
    }

    suspend fun update(indonesiaItem: Covid19IndonesiaItem) {
        covid19IndonesiaDao.update(indonesiaItem)
    }
}