package com.example.recyclerview.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "universities")
data class UniversityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L, // Assuming "id" is a unique identifier for universities
    val web_pages: List<String>,
    val alpha_two_code: String,
    val country: String,
    val name: String,
    val state_province: String?,
    val domains: List<String>
)
