package com.example.recyclerview.hiltmodules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import com.example.recyclerview.network.UniversityApi
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UniversityModule {

    private const val BASE_URL = "http://universities.hipolabs.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUniversityApi(retrofit: Retrofit): UniversityApi {
        return retrofit.create(UniversityApi::class.java)
    }
}
