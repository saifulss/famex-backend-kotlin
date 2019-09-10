package com.saifulsandbox.famex.services

import com.saifulsandbox.famex.entities.Category
import com.saifulsandbox.famex.repositories.CategoryRepository
import com.saifulsandbox.famex.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
        private val categoryRepository: CategoryRepository,
        private val userRepository: UserRepository
) {
    fun createNewCategory(name: String): Category {
        val newCategory = Category(null, name, null)
        categoryRepository.save(newCategory)
        return newCategory
    }

//    fun getAll(): List<Category> = categoryRepository.findAll()

    fun getById(id: Long) = categoryRepository.getOne(id)

//    fun save(category: Category): Category = categoryRepository.save(category)
}