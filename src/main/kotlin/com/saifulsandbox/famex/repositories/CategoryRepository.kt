package com.saifulsandbox.famex.repositories

import com.saifulsandbox.famex.entities.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long>