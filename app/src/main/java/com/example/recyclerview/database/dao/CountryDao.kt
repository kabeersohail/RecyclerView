package com.example.recyclerview.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recyclerview.database.entities.CountryEntity
import com.example.recyclerview.models.SimpleCountryData

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCountries(countries: List<CountryEntity>)

    @Query("SELECT name, pngFlagUrl, commonName FROM countries")
    suspend fun getAllCountries(): List<SimpleCountryData>
}
