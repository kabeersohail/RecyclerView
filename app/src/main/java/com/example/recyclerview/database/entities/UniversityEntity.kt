package com.example.recyclerview.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "universities")
data class UniversityEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val web_pages: List<String>,
    val alpha_two_code: String,
    val country: String,
    val state_province: String?,
    val domains: List<String>
)
