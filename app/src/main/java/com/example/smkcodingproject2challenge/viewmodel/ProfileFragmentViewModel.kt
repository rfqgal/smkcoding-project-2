package com.example.smkcodingproject2challenge.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smkcodingproject2challenge.ProfileModel
import com.example.smkcodingproject2challenge.database.ProfileDatabase
import com.example.smkcodingproject2challenge.repos.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragmentViewModel() : ViewModel() {

    lateinit var repository: ProfileRepository

    lateinit var allIdentities: LiveData<List<ProfileModel>>

    fun init(context: Context) {
        val profileDao = ProfileDatabase.getDatabase(context).profileDao()
        repository = ProfileRepository(profileDao)
        allIdentities = repository.allIdentities
    }

    fun delete(profileModel: ProfileModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(profileModel)
    }

    fun insertAll(profileModels: List<ProfileModel>) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
        repository.insertAll(profileModels)
    }
}
