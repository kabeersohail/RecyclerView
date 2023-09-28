package com.example.recyclerview.extensions

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