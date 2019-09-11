package com.saifulsandbox.famex.repositories

import com.saifulsandbox.famex.entities.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CategoryRepository : JpaRepository<Category, Long> {
    @Query("SELECT e.id FROM #{#entityName} e")
    fun getAllIds(): List<Long>
}