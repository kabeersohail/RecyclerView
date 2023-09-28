package com.example.recyclerview.models

data class University(
    val web_pages: List<String>,
    val alpha_two_code: String,
    val country: String,
    val name: String,
    val state_province: String?, // Use nullable type if the field can be null
    val domains: List<String>
)
