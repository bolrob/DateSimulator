package ru.belichenko.datesimulator.dto

data class ReactionForm(
    val idFrom: Long,
    val idTo: Long,
    var reaction: Boolean,
) {}