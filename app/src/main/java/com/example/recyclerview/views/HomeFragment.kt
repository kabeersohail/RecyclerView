package com.example.recyclerview.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.adapters.CountryAdapter
import com.example.recyclerview.database.dao.CountryLastUpdateTimestampDao
import com.example.recyclerview.database.dao.UniversityDao
import com.example.recyclerview.databinding.FragmentHomeBinding
import com.example.recyclerview.extensions.TAG
import com.example.recyclerview.network.CountryApi
import com.example.recyclerview.network.UniversityApi
import com.example.recyclerview.repository.CountryRepository
import com.example.recyclerview.repository.UniversityRepository
import com.example.recyclerview.viewmodelfactory.UniversityViewModelFactory
import com.example.recyclerview.viewmodels.UniversityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @Inject
    @Named("countryRetrofit")
    lateinit var countryApi: CountryApi

    @Inject
    @Named("universityRetrofit")
    lateinit var universityApi: UniversityApi

    @Inject
    lateinit var universityDao: UniversityDao

    @Inject
    lateinit var countryLastUpdateTimestampDao: CountryLastUpdateTimestampDao

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val universityRepository = UniversityRepository(universityApi, universityDao, countryLastUpdateTimestampDao)
        val countryRepository = CountryRepository(countryApi)
        val viewModelFactory = UniversityViewModelFactory(universityRepository,countryRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[UniversityViewModel::class.java]

        val countryRecyclerView: RecyclerView = binding.recyclerViewCountry
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        countryRecyclerView.layoutManager = layoutManager

//        binding.fetchUniversities.setOnClickListener {
//            viewModel.getUniversities("India")
//        }

        viewModel.getUniversitiesLiveData().observe(requireActivity()) {
            Log.d(TAG, "$it")
        }

        viewModel.getCountries()

        viewModel.getCountriesLiveData().observe(requireActivity()) { countryResponse ->
            val countryAdapter = CountryAdapter(countryResponse)
            countryRecyclerView.adapter = countryAdapter
        }
    }
}