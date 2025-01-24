package ru.belichenko.datesimulator.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import ru.belichenko.datesimulator.dto.Profile
import ru.belichenko.datesimulator.dto.RegisterForm
import ru.belichenko.datesimulator.service.UserService

@RestController
class UsersController(
    val userService: UserService
) {
    @PostMapping("/users")
    fun register(@RequestBody form: RegisterForm) = userService.register(form)

    @PutMapping("/users")
    fun updateUser(@RequestBody profile: Profile, @RequestHeader("authorization") token: String) = userService.updateUser(profile, token)

    @GetMapping("/users")
    fun getUsers(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sortBy: String, @RequestHeader("authorization") token: String)
        = userService.getUsers(page, size, sortBy, token)

    @PostMapping("/users/{id}/reaction")
    fun reactUser(@RequestBody reaction: Boolean, @PathVariable id: Long, @RequestHeader("authorization") token: String)
        = userService.react(reaction, id, token)
}