package com.saifulsandbox.famex.services

import com.saifulsandbox.famex.entities.ExpenseClaim
import com.saifulsandbox.famex.repositories.ExpenseClaimRepository
import com.saifulsandbox.famex.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExpenseClaimService(val expenseClaimRepository: ExpenseClaimRepository, val userRepository: UserRepository) {

    fun createNewExpenseClaim(amount: Long, name: String, payerId: Long): ExpenseClaim {
        val currentUser = userRepository.findByIdOrNull(payerId)
                ?: throw Exception("User with ID $payerId cannot be found.")

        val newClaim = ExpenseClaim(name = name, amount = amount, createdAt = Date(), payer = currentUser)
        expenseClaimRepository.save(newClaim)
        return newClaim
    }

    fun getAll(): List<ExpenseClaim> = expenseClaimRepository.findAll()

    fun getById(id: Long) = expenseClaimRepository.getOne(id)

    fun save(expenseClaim: ExpenseClaim): ExpenseClaim = expenseClaimRepository.save(expenseClaim)
}