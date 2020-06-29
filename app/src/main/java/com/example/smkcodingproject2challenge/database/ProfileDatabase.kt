package com.example.smkcodingproject2challenge.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smkcodingproject2challenge.ProfileModel
import com.example.smkcodingproject2challenge.dao.ProfileDao

@Database(entities = [ProfileModel::class], version = 1, exportSchema = false)
public abstract class ProfileDatabase : RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile
        private var INSTANCE: ProfileDatabase? = null

        fun getDatabase(context: Context): ProfileDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProfileDatabase::class.java,
                    "identity_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}