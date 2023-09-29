package com.example.recyclerview.extensions

import android.content.Context
import android.net.ConnectivityManager
import com.example.recyclerview.database.entities.UniversityEntity
import com.example.recyclerview.models.University

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if(tag.length <= 27) tag else tag.substring(0, 23)
    }

fun UniversityEntity.toUniversity(): University {
    return University(
        web_pages = web_pages,
        alpha_two_code = alpha_two_code,
        country = country,
        name = name,
        state_province = state_province,
        domains = domains
    )
}

fun University.toUniversityEntity(): UniversityEntity {
    return UniversityEntity(
        web_pages = web_pages,
        alpha_two_code = alpha_two_code,
        country = country,
        name = name,
        state_province = state_province,
        domains = domains
    )
}

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}
