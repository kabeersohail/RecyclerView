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
        val isDataStale = isDataStaleForCountry(country)

        return if (isDataStale) {
            // Data found in the local database, but it's stale, fetch fresh data
            val freshData = api.searchUniversities(country)

            // Save the fresh data to the local database
            universityDao.insertUniversities(freshData.map { it.toUniversityEntity() })

            // Create a CountryLastUpdateTimestampEntity instance
            val entity = CountryLastUpdateTimestampEntity(country = country, lastUpdated = System.currentTimeMillis())

            // Call the DAO method to update the timestamp
            countryTimestampDao.updateLastUpdatedTimestamp(entity)

            freshData
        } else {
            // Data found in the local database, return it
            val cachedData = universityDao.getUniversitiesByCountry(country)
            cachedData.map { it.toUniversity() }
        }
    }

    private suspend fun isDataStaleForCountry(country: String): Boolean {
        val currentTimeMillis = System.currentTimeMillis()
        val refreshIntervalMillis = 5000 // 5 seconds (adjust as needed)
        val lastUpdatedTimestamp = countryTimestampDao.getLastUpdatedTimestamp(country)
        return (lastUpdatedTimestamp == null) || ((currentTimeMillis - lastUpdatedTimestamp) >= refreshIntervalMillis)
    }
}

