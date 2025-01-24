package ru.belichenko.datesimulator.repository

import org.springframework.stereotype.Component
import ru.belichenko.datesimulator.dao.RegisterResponse
import ru.belichenko.datesimulator.dto.User
import ru.belichenko.datesimulator.dto.RegisterForm
import ru.belichenko.datesimulator.dto.Profile

@Component
class UserRepository(
    val userRepository: MutableList<User>
) {
    private fun generateToken(length: Int=16): String {
        return (1..length).map {(('a'..'z') + ('A'..'Z') + ('0'..'9')).random() }.joinToString { "" }
    }
    fun getUserByToken(token: String): User? {
        return userRepository.find { it.token == token }
    }
    fun getProfilesByPage(page: Int, size: Int, sortBy: String): List<Profile> {
        val list = userRepository.map {
                user -> Profile(
            gender = user.profile?.gender ?: Profile.Sex.NULL,
            age = user.profile?.age ?: 0,
            firstName = user.profile?.firstName ?: "",
            lastName =  user.profile?.lastName  ?: "",
            photo = user.profile?.photo ?: "",
            )
        }.sortedBy { it.lastName } //тупая сортировка я ее маму в кино водил как ее делать аааа
        if (page in (1..(userRepository.size / size).toInt())) {
            return list.slice((page - 1) * size..<page * size)
        }
        return listOf()

    }
    fun register(form: RegisterForm): RegisterResponse {
        if (userRepository.any {it.account.login == form.login}) {
            return RegisterResponse(-1, "")
        }
        val id = userRepository.size.toLong()
        val token = generateToken()
        userRepository.add(User(null, form, id, token))
        return RegisterResponse(id, token)
    }
    fun update(newUser: Profile, token: String): Long {
        val user = getUserByToken(token)
        if (user != null) {
            val index = userRepository.indexOf(getUserByToken(token))
            userRepository[index].profile = newUser
            return user.id
        } else {
            return -1
        }
    }
}