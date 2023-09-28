package com.example.recyclerview.repository

import com.example.recyclerview.database.dao.CountryLastUpdateTimestampDao
import com.example.recyclerview.database.dao.UniversityDao
import com.example.recyclerview.database.entities.CountryLastUpdateTimestampEntity
import com.example.recyclerview.extensions.toUniversity
import com.example.recyclerview.extensions.toUniversityEntity
import com.example.recyclerview.models.University
import com.example.recyclerview.network.UniversityApi

class UniversityRepository(
    private val api: UniversityApi,
    private val universityDao: UniversityDao,
    private val countryTimestampDao: CountryLastUpdateTimestampDao
) {

    suspend fun searchUniversities(country: String): List<University> {
        val cachedData = universityDao.getUniversitiesByCountry(country)
        val isDataStale = isDataStaleForCountry(country)

        return if (cachedData.isNotEmpty()) {
            // Data found in the local database, return it immediately to the user
            if (isDataStale) {
                // If data is stale, start a background refresh
                refreshDataInBackground(country)
            }
            cachedData.map { it.toUniversity() }
        } else {
            // Data not found locally, fetch fresh data from the API
            val freshData = api.searchUniversities(country)
            // Save the fresh data to the local database
            universityDao.insertUniversities(freshData.map { it.toUniversityEntity() })
            // Update the last updated timestamp
            updateLastUpdatedTimestamp(country)
            freshData
        }
    }

    private suspend fun isDataStaleForCountry(country: String): Boolean {
        val currentTimeMillis = System.currentTimeMillis()
        val refreshIntervalMillis = 5000 // 5 seconds (adjust as needed)
        val lastUpdatedTimestamp = countryTimestampDao.getLastUpdatedTimestamp(country)
        return (lastUpdatedTimestamp == null) || ((currentTimeMillis - lastUpdatedTimestamp) >= refreshIntervalMillis)
    }

    private suspend fun refreshDataInBackground(country: String) {
        // Perform an asynchronous refresh of data in the background
        val freshData = api.searchUniversities(country)
        // Save the fresh data to the local database
        universityDao.insertUniversities(freshData.map { it.toUniversityEntity() })
        // Update the last updated timestamp
        updateLastUpdatedTimestamp(country)
        // Check if the fresh data is different from the cached data
        val cachedData = universityDao.getUniversitiesByCountry(country)
        if (cachedData.isNotEmpty() && freshData != cachedData.map { it.toUniversity() }) {
            // Notify the user that data has been updated (e.g., through a UI element)
            // You can implement this notification as needed
        }
    }

    private suspend fun updateLastUpdatedTimestamp(country: String) {
        val entity = CountryLastUpdateTimestampEntity(country = country, lastUpdated = System.currentTimeMillis())
        countryTimestampDao.updateLastUpdatedTimestamp(entity)
    }
}


