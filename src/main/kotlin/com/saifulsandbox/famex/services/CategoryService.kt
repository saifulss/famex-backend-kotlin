package com.saifulsandbox.famex.services

import com.saifulsandbox.famex.entities.Category
import com.saifulsandbox.famex.repositories.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository
) {
    fun createNewCategory(name: String): Category {
        val newCategory = Category(null, name, null)
        categoryRepository.save(newCategory)
        return newCategory
    }

    fun getById(id: Long) = categoryRepository.getOne(id)

    fun getAllIds() = categoryRepository.getAllIds()
}