package ru.belichenko.datesimulator.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.belichenko.datesimulator.dao.UserResponse
import ru.belichenko.datesimulator.dto.Profile
import ru.belichenko.datesimulator.repository.UserRepository
import org.springframework.http.HttpStatus
import ru.belichenko.datesimulator.dao.ReactionResponse
import ru.belichenko.datesimulator.dao.RegisterResponse
import ru.belichenko.datesimulator.dto.ReactionForm
import ru.belichenko.datesimulator.dto.RegisterForm
import ru.belichenko.datesimulator.repository.ReactionRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val reactionRepository: ReactionRepository,
    ) {
    fun register(form: RegisterForm): ResponseEntity<RegisterResponse> {
        val response = userRepository.register(form)
        return if (response.id != (-1).toLong()) {
            ResponseEntity(response, HttpStatus.OK)
        } else {
            ResponseEntity(response, HttpStatus.MULTI_STATUS)
        }
    }
    fun updateUser(profile: Profile, token: String): ResponseEntity<UserResponse> {
        val id = userRepository.update(profile, token)
        return if (id != (-1).toLong()) {
            ResponseEntity(UserResponse(id, profile), HttpStatus.OK)
        } else {
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        }
    }
    fun getUsers(page: Int, size: Int, sortBy: String, token: String): ResponseEntity<List<Profile>> {
        if (userRepository.getUserByToken(token) != null) {
            val list = userRepository.getProfilesByPage(page, size, sortBy)
            return ResponseEntity(list, HttpStatus.OK)
        }
        return ResponseEntity(listOf(), HttpStatus.NOT_FOUND)
    }
    fun react(reaction: Boolean, idTo: Long, token: String): ResponseEntity<ReactionResponse> {
        val user = userRepository.getUserByToken(token)
        if (user != null) {
            reactionRepository.react(reaction, user.id, idTo)
            ResponseEntity(ReactionResponse(reaction), HttpStatus.OK)
        }
        return ResponseEntity(ReactionResponse(false), HttpStatus.NOT_FOUND)
    }
}