package com.saifulsandbox.famex.springutils

import com.saifulsandbox.famex.dtos.UserDto
import com.saifulsandbox.famex.entities.User
import com.saifulsandbox.famex.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthUtils {
    @Autowired
    lateinit var userService: UserService

    fun getCurrentUser(): User {
        val userDto = SecurityContextHolder.getContext().authentication.principal as UserDto
        return userService.getByEmail(userDto.email).first() ?: throw Exception("No user found.")
    }
}
