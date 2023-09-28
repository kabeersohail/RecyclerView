package com.example.recyclerview.hiltmodules

import com.example.recyclerview.network.CountryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CountryModule {

    private const val COUNTRY_BASE_URL = "https://restcountries.com/v3.1/"

    @Provides
    @Singleton
    @Named("countryRetrofit")
    fun provideCountryRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(COUNTRY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("countryRetrofit")
    fun provideCountryApi(@Named("countryRetrofit") retrofit: Retrofit): CountryApi {
        return retrofit.create(CountryApi::class.java)
    }
}
