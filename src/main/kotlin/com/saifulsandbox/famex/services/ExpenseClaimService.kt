package com.saifulsandbox.famex.services

import com.saifulsandbox.famex.entities.ExpenseClaim
import com.saifulsandbox.famex.repositories.CategoryRepository
import com.saifulsandbox.famex.repositories.ExpenseClaimRepository
import com.saifulsandbox.famex.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ExpenseClaimService(
        private val expenseClaimRepository: ExpenseClaimRepository,
        private val userRepository: UserRepository,
        private val categoryRepository: CategoryRepository
) {

    fun createNewExpenseClaim(amount: Long, categoryId: Long, description: String? = null, payerId: Long): ExpenseClaim {
        val currentUser = userRepository.findByIdOrNull(payerId)
                ?: throw Exception("User with ID $payerId cannot be found.")

        val category = categoryRepository.findByIdOrNull(categoryId)
                ?: throw java.lang.Exception("Category with ID $categoryId cannot be found.")

        val newClaim = ExpenseClaim(
                null,
                amount,
                category,
                currentUser,
                description,
                null,
                LocalDateTime.now()
        )

        expenseClaimRepository.save(newClaim)
        return newClaim
    }

    fun getAll(): List<ExpenseClaim> = expenseClaimRepository.findAll()

    fun getById(id: Long) = expenseClaimRepository.getOne(id)

    fun save(expenseClaim: ExpenseClaim): ExpenseClaim = expenseClaimRepository.save(expenseClaim)
}