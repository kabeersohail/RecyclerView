package com.example.recyclerview.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recyclerview.database.dao.CountryDao
import com.example.recyclerview.database.dao.CountryLastUpdateTimestampDao
import com.example.recyclerview.database.dao.UniversityDao
import com.example.recyclerview.database.entities.CountryEntity
import com.example.recyclerview.database.entities.CountryLastUpdateTimestampEntity
import com.example.recyclerview.database.entities.UniversityEntity
import com.example.recyclerview.database.typeconverters.ListStringConverter

@Database(entities = [UniversityEntity::class, CountryLastUpdateTimestampEntity::class, CountryEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListStringConverter::class)
abstract class UniversityDatabase : RoomDatabase() {

    abstract fun universityDao(): UniversityDao
    abstract fun countryLastUpdateTimestampDao(): CountryLastUpdateTimestampDao
    abstract fun countryDao(): CountryDao // Include the CountryDao here

    companion object {
        @Volatile
        private var INSTANCE: UniversityDatabase? = null

        fun getDatabase(context: Context): UniversityDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UniversityDatabase::class.java,
                    "university_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
