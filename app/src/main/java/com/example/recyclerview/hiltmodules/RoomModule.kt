package com.example.recyclerview.hiltmodules

import android.app.Application
import androidx.room.Room
import com.example.recyclerview.database.UniversityDatabase
import com.example.recyclerview.database.dao.UniversityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideUniversityDatabase(application: Application): UniversityDatabase {
        return Room.databaseBuilder(
            application,
            UniversityDatabase::class.java,
            "university_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUniversityDao(database: UniversityDatabase): UniversityDao {
        return database.universityDao()
    }
}
