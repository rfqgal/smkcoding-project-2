package com.example.smkcodingproject2challenge.database

import android.content.Context
import androidx.room.*
import com.example.smkcodingproject2challenge.api.Covid19IndonesiaItem
import com.example.smkcodingproject2challenge.dao.Covid19IndonesiaDao

@Database(entities = [Covid19IndonesiaItem::class], version = 1, exportSchema = false)
public abstract class Covid19IndonesiaDatabase : RoomDatabase() {

    abstract fun covid19IndonesiaDao(): Covid19IndonesiaDao

    companion object {
        @Volatile
        private var INSTANCE: Covid19IndonesiaDatabase? = null

        fun getDatabase(context: Context): Covid19IndonesiaDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Covid19IndonesiaDatabase::class.java,
                    "covid19_indonesia_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}