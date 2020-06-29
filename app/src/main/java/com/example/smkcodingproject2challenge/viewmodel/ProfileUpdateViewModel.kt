package com.example.smkcodingproject2challenge.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smkcodingproject2challenge.ProfileModel
import com.example.smkcodingproject2challenge.database.ProfileDatabase
import com.example.smkcodingproject2challenge.repos.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileUpdateViewModel() : ViewModel() {

    lateinit var repository: ProfileRepository

    fun init(context: Context) {
        val profileDao = ProfileDatabase.getDatabase(context).profileDao()
        repository = ProfileRepository(profileDao)
    }

    fun updateData(profileModel: ProfileModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(profileModel)
    }
}
