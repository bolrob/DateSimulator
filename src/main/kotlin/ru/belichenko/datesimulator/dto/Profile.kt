package ru.belichenko.datesimulator.dto

data class Profile(
    val gender: Sex,
    val age: Int,
    val firstName: String,
    val lastName: String,
    val photo: String,
) {
    enum class Sex {
        MALE, FEMALE, NULL
    }
}