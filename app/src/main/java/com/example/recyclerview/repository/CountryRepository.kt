package com.example.recyclerview.repository

import com.example.recyclerview.database.dao.CountryDao
import com.example.recyclerview.database.entities.CountryEntity
import com.example.recyclerview.models.CountryResponse
import com.example.recyclerview.models.SimpleCountryData
import com.example.recyclerview.network.CountryApi

class CountryRepository(private val api: CountryApi, private val countryDao: CountryDao) {

    suspend fun getCountries(): List<SimpleCountryData> {
        val cachedData = countryDao.getAllCountries()

        if (cachedData.isNotEmpty()) {
            return cachedData
        }

        val freshData = api.getCountries()
        val mappedData = freshData.map { it.toCountryEntity() }
        countryDao.insertAllCountries(mappedData)

        return freshData.map { it.toSimpleCountryData() }
    }

    private fun CountryResponse.toCountryEntity(): CountryEntity {
        return CountryEntity(name.common,name.official ,flags.png)
    }
}

fun CountryResponse.toSimpleCountryData(): SimpleCountryData {
    return SimpleCountryData(name.common,name.official, flags.png)
}


