package com.example.smkcodingproject2challenge.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.smkcodingproject2challenge.ProfileModel

@Dao
interface ProfileDao {
    @Query("SELECT * FROM identity")
    fun getAllProfile(): LiveData<List<ProfileModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profileModel: ProfileModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(profileModels: List<ProfileModel>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(profileModel: ProfileModel)

    @Delete()
    suspend fun delete(profileModel: ProfileModel)

    @Query("DELETE FROM identity")
    suspend fun deleteAll()
}