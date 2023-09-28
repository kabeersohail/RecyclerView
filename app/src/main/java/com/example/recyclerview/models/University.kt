package com.example.recyclerview.models

import com.google.gson.annotations.SerializedName

data class University(
    val web_pages: List<String>,
    val alpha_two_code: String,
    val country: String,
    val name: String,
    @SerializedName("state-province")
    val state_province: String?,
    val domains: List<String>
)
