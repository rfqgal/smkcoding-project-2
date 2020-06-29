package com.example.smkcodingproject2challenge.repos

import androidx.lifecycle.LiveData
import com.example.smkcodingproject2challenge.ProfileModel
import com.example.smkcodingproject2challenge.dao.ProfileDao

class ProfileRepository(private val profileDao: ProfileDao) {

    val allIdentities: LiveData<List<ProfileModel>> = profileDao.getAllProfile()

    suspend fun insert(profileModel: ProfileModel) {
        profileDao.insert(profileModel)
    }

    suspend fun insertAll(profileModels: List<ProfileModel>) {
        profileDao.insertAll(profileModels)
    }

    suspend fun delete(profileModel: ProfileModel) {
        profileDao.delete(profileModel)
    }

    suspend fun deleteAll() {
        profileDao.deleteAll()
    }

    suspend fun update(profileModel: ProfileModel) {
        profileDao.update(profileModel)
    }
}