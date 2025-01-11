package ru.belichenko.datesimulator.dto

data class UserDto(
    var gender: Sex,
    var age: Int,
    var firstName: String,
    var lastName: String,
    var photo: String,
){
    enum class Sex{
        MALE, FEMALE
    }
}