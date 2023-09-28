package com.example.recyclerview.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recyclerview.database.entities.CountryLastUpdateTimestampEntity

@Dao
interface CountryLastUpdateTimestampDao {

    @Query("SELECT lastUpdated FROM country_last_update_timestamp WHERE country = :country")
    suspend fun getLastUpdatedTimestamp(country: String): Long?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLastUpdatedTimestamp(entity: CountryLastUpdateTimestampEntity)

}
