package com.example.recyclerview.extensions

val Any.TAG: String
    get() {
        val tag = javaClass.simpleName
        return if(tag.length <= 27) tag else tag.substring(0, 23)
    }