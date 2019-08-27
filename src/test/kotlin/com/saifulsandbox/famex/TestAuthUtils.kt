package com.saifulsandbox.famex

import com.saifulsandbox.famex.constants.SecurityConstants
import com.saifulsandbox.famex.entities.User
import com.saifulsandbox.famex.security.CustomUserDetails
import com.saifulsandbox.famex.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TestAuthUtils {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    lateinit var jwtUtils: JwtUtils

    fun getQuickToken(): String = SecurityConstants.TOKEN_PREFIX + " " + generateUserAndToken()

    fun generateUserAndToken(): String {
        val randomLong = "user${Date().time}"
        val newUser = userService.createNewUser("user$randomLong@example.com", "secret", "user$randomLong")

        return generateToken(newUser)
    }

    private fun generateToken(user: User) = jwtUtils.generateToken(CustomUserDetails(user))
}