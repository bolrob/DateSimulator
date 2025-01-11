package ru.belichenko.datesimulator.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import ru.belichenko.datesimulator.dto.UserDto

@RestController
class UsersController {

    @GetMapping("/users")
    fun getUsers(@PageableDefault(size=20) pageable: Pageable): Page<UserDto> {
        println("Page: ${pageable.pageNumber}")
        println("Page: ${pageable.pageNumber}")
        println("Page: ${pageable.pageNumber}")
        return PageImpl<UserDto>(listOf(UserDto(
            gender = UserDto.Sex.FEMALE,
            age = 16,
            firstName = "Илья",
            lastName = "Сергеев",
            photo = "pid"
        )))
    }
}