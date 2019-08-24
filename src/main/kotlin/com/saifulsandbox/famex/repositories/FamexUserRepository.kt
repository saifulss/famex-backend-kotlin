package com.saifulsandbox.famex.repositories

import com.saifulsandbox.famex.entities.FamexUser
import org.springframework.data.jpa.repository.JpaRepository

interface FamexUserRepository : JpaRepository<FamexUser, Long> {
    override fun count(): Long
}