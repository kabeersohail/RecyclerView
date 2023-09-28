package com.example.recyclerview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerview.models.CountryResponse
import com.example.recyclerview.models.University
import com.example.recyclerview.repository.CountryRepository
import com.example.recyclerview.repository.UniversityRepository
import kotlinx.coroutines.launch

class UniversityViewModel(
    private val repository: UniversityRepository,
    private val countryRepository: CountryRepository
) : ViewModel() {

    private val universitiesLiveData = MutableLiveData<List<University>>()
    private val countriesLiveData = MutableLiveData<List<CountryResponse>>() // LiveData for countries

    fun getUniversities(country: String) {
        viewModelScope.launch {
            try {
                val universities = repository.searchUniversities(country)
                universitiesLiveData.value = universities
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun getCountries() {
        viewModelScope.launch {
            try {
                val countries = countryRepository.getCountries()
                countriesLiveData.value = countries
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }

    fun getUniversitiesLiveData(): LiveData<List<University>> {
        return universitiesLiveData
    }


    fun getCountriesLiveData(): LiveData<List<CountryResponse>> {
        return countriesLiveData
    }
}
