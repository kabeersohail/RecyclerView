package com.example.recyclerview.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recyclerview.database.dao.UniversityDao
import com.example.recyclerview.database.entities.UniversityEntity
import com.example.recyclerview.database.typeconverters.ListStringConverter

@Database(entities = [UniversityEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListStringConverter::class)
abstract class UniversityDatabase : RoomDatabase() {

    abstract fun universityDao(): UniversityDao

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
