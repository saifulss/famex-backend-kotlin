package com.saifulsandbox.famex.services

import com.saifulsandbox.famex.repositories.UserRepository
import com.saifulsandbox.famex.security.CustomUserDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService : UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String): UserDetails {
        val loadedUser = userRepository.getByEmail(username).first() ?: throw Exception("User not found.")
        return CustomUserDetails(loadedUser)
    }
}