package com.example.recyclerview.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country_last_update_timestamp")
data class CountryLastUpdateTimestampEntity(
    @PrimaryKey
    val country: String,
    val lastUpdated: Long // Store timestamps in milliseconds
)
