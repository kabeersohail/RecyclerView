package com.example.recyclerview.models

data class CountryResponse(
    val flags: FlagsResponse,
    val name: NameResponse
)

data class FlagsResponse(
    val png: String,
    val svg: String,
    val alt: String
)

data class NameResponse(
    val common: String,
    val official: String,
    val nativeName: NativeNameResponse
)

data class NativeNameResponse(
    val fra: FraResponse
)

data class FraResponse(
    val official: String,
    val common: String
)
