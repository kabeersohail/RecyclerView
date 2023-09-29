package com.example.recyclerview.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.adapters.CountryAdapter
import com.example.recyclerview.adapters.UniversityAdapter
import com.example.recyclerview.database.dao.CountryDao
import com.example.recyclerview.database.dao.CountryLastUpdateTimestampDao
import com.example.recyclerview.database.dao.UniversityDao
import com.example.recyclerview.databinding.FragmentHomeBinding
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
    lateinit var countryDao: CountryDao

    @Inject
    lateinit var countryLastUpdateTimestampDao: CountryLastUpdateTimestampDao

    private lateinit var binding: FragmentHomeBinding
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var universityAdapter: UniversityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val universityRepository =
            UniversityRepository(requireContext(), universityApi, universityDao, countryLastUpdateTimestampDao)
        val countryRepository = CountryRepository(countryApi, countryDao)
        val viewModelFactory = UniversityViewModelFactory(universityRepository, countryRepository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[UniversityViewModel::class.java]

        val countryRecyclerView: RecyclerView = binding.recyclerViewCountry
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        countryRecyclerView.layoutManager = layoutManager

        // Initialize the adapters
        countryAdapter = CountryAdapter(emptyList()) { selectedCountry ->
            // When a country is selected, fetch universities for that country
            viewModel.getUniversities(selectedCountry)
        }
        universityAdapter = UniversityAdapter(emptyList())

        countryRecyclerView.adapter = countryAdapter

        // Observe changes in the country list
        viewModel.getCountriesLiveData().observe(requireActivity()) { simpleCountryData ->
            countryAdapter.updateData(simpleCountryData)

            val targetCountryName = "India"

            val indexOfTargetCountry = simpleCountryData.indexOfFirst { response ->
                val commonName = response.name
                val officialName = response.commonName
                commonName.equals(targetCountryName, ignoreCase = true) ||
                        officialName.equals(targetCountryName, ignoreCase = true)
            }

            layoutManager.scrollToPosition(indexOfTargetCountry)
            viewModel.getUniversities(targetCountryName)
        }

        val universityRecyclerView: RecyclerView = binding.recyclerViewUniversities
        val universityLayoutManager = LinearLayoutManager(requireContext())
        universityRecyclerView.layoutManager = universityLayoutManager
        universityRecyclerView.adapter = universityAdapter

        // Observe changes in the university list
        viewModel.getUniversitiesLiveData().observe(requireActivity()) { universities ->
            universityAdapter.updateData(universities)
        }

        // Fetch the initial list of countries
        viewModel.getCountries()
    }
}
