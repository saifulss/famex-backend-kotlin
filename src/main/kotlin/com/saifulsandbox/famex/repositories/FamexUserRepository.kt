package com.saifulsandbox.famex.repositories

import com.saifulsandbox.famex.entities.FamexUser
import org.springframework.data.repository.CrudRepository

interface FamexUserRepository : CrudRepository<FamexUser, Long> {
    override fun count(): Long
}