package com.example.recyclerview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recyclerview.models.University
import com.example.recyclerview.repository.UniversityRepository
import kotlinx.coroutines.launch

class UniversityViewModel(private val repository: UniversityRepository) : ViewModel() {

    private val universitiesLiveData = MutableLiveData<List<University>>()

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

    fun getUniversitiesLiveData(): LiveData<List<University>> {
        return universitiesLiveData
    }
}
