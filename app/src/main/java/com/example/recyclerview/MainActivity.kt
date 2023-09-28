package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerview.extensions.TAG
import com.example.recyclerview.network.UniversityApi
import com.example.recyclerview.repository.UniversityRepository
import com.example.recyclerview.viewmodelfactory.UniversityViewModelFactory
import com.example.recyclerview.viewmodels.UniversityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var universityApi: UniversityApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val universityRepository = UniversityRepository(universityApi)
        val viewModelFactory = UniversityViewModelFactory(universityRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[UniversityViewModel::class.java]

        viewModel.getUniversities("India")

        viewModel.getUniversitiesLiveData().observe(this) {
            Log.d(TAG, "$it")
        }

    }
}