package com.example.recyclerview.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recyclerview.database.entities.UniversityEntity

@Dao
interface UniversityDao {

    @Query("SELECT * FROM universities WHERE country = :country")
    suspend fun getUniversitiesByCountry(country: String): List<UniversityEntity>

    @Query("SELECT * FROM universities")
    fun getAllUniversities(): LiveData<List<UniversityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUniversities(universities: List<UniversityEntity>)
}
