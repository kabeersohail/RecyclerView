package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.recyclerview.extensions.TAG
import com.example.recyclerview.network.RetrofitClient.retrofit
import com.example.recyclerview.network.UniversityApi
import com.example.recyclerview.repository.UniversityRepository
import com.example.recyclerview.viewmodelfactory.UniversityViewModelFactory
import com.example.recyclerview.viewmodels.UniversityViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val universityApi = retrofit.create(UniversityApi::class.java)
        val universityRepository = UniversityRepository(universityApi)
        val viewModelFactory = UniversityViewModelFactory(universityRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[UniversityViewModel::class.java]

        viewModel.getUniversities("India")

        viewModel.getUniversitiesLiveData().observe(this) {
            Log.d(TAG, "$it")
        }

    }
}