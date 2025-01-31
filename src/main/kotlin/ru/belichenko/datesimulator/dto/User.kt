package ru.belichenko.datesimulator.dto

data class User(
    var profile: Profile?,
    val account: RegisterForm,
    val id: Long,
    val token: String,
){
}