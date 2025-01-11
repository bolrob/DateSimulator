package ru.belichenko.datesimulator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DateSimulatorApplication

fun main(args: Array<String>) {
	runApplication<DateSimulatorApplication>(*args)
}
