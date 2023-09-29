package com.example.recyclerview.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey
    val name: String,
    val commonName: String,
    val pngFlagUrl: String
)
