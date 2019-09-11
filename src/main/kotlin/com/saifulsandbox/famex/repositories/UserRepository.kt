package com.saifulsandbox.famex.repositories

import com.saifulsandbox.famex.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {
    fun getByEmail(email: String): List<User?>

    @Query("SELECT e.id FROM #{#entityName} e")
    fun getAllIds(): List<Long>
}