package com.example.recyclerview.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerview.repository.UniversityRepository
import com.example.recyclerview.viewmodels.UniversityViewModel

class UniversityViewModelFactory(private val repository: UniversityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("unchecked_cast")
        if (modelClass.isAssignableFrom(UniversityViewModel::class.java)) {
            return UniversityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

