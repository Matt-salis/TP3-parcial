package com.example.parcial_grupo_8_ya.data.model.User

data class Address(
    val city: String,
    val geolocation: Geolocation,
    val number: Int,
    val street: String,
    val zipcode: String
)