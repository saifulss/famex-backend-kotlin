package com.saifulsandbox.famex.services

import com.github.javafaker.Faker
import com.saifulsandbox.famex.entities.Category
import com.saifulsandbox.famex.entities.User
import com.saifulsandbox.famex.repositories.CategoryRepository
import com.saifulsandbox.famex.repositories.ExpenseClaimRepository
import com.saifulsandbox.famex.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class FakerService(
        private val categoryRepository: CategoryRepository,
        private val userRepository: UserRepository,
        private val expenseClaimRepository: ExpenseClaimRepository,
        private val userService: UserService
) {
    private val faker = Faker()

    fun createRandomCategory(): Category {
        return categoryRepository.save(Category(
                null,
                faker.lorem().word()
        ))
    }

    fun createRandomUser(): User {
        val name = faker.name()
        val fullName = "${name.firstName()} ${name.lastName()}"
        val displayName = fullName.toLowerCase().replace(" ", "_")
        val email = "$displayName@example.com"

        return userService.createNewUser(
                email,
                "secret",
                fullName
        )
    }
}