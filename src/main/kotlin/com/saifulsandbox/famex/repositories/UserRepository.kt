package com.saifulsandbox.famex.repositories

import com.saifulsandbox.famex.entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    override fun count(): Long

    fun getByEmail(email: String): List<User?>
}