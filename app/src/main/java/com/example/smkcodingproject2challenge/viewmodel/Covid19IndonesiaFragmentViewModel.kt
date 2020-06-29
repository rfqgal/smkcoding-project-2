package com.example.smkcodingproject2challenge.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smkcodingproject2challenge.api.Covid19IndonesiaItem
import com.example.smkcodingproject2challenge.database.Covid19IndonesiaDatabase
import com.example.smkcodingproject2challenge.repos.Covid19IndonesiaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Covid19IndonesiaFragmentViewModel(): ViewModel() {

    lateinit var repository: Covid19IndonesiaRepository

    lateinit var allIndonesiaItems: LiveData<List<Covid19IndonesiaItem>>

    fun init(context: Context) {
        val covid19IndonesiaDao = Covid19IndonesiaDatabase.getDatabase(context).covid19IndonesiaDao()
        repository = Covid19IndonesiaRepository(covid19IndonesiaDao)
        allIndonesiaItems = repository.allIndonesiaItem
    }

    fun delete(indonesiaItem: Covid19IndonesiaItem) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(indonesiaItem)
    }
    // Launching a new coroutine to insert the data in a non-blocking way
    fun insertAll(indonesiaItems: List<Covid19IndonesiaItem>) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
        repository.insertAll(indonesiaItems)
    }
}