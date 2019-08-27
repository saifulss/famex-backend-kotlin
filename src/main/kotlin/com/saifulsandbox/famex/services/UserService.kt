package com.saifulsandbox.famex.services

import com.saifulsandbox.famex.entities.User
import com.saifulsandbox.famex.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService(private val userRepository: UserRepository) {

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    @Transactional
    fun createNewUser(email: String, password: String, displayName: String): User {
        val newUser = User(displayName = displayName, email = email, password = passwordEncoder.encode(password))
        userRepository.save(newUser)
        return newUser
    }

    fun getAll(): List<User> = userRepository.findAll()

    fun getByEmail(email: String) = userRepository.getByEmail(email)
}