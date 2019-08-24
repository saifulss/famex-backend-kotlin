package com.saifulsandbox.famex.services

import com.saifulsandbox.famex.entities.ExpenseClaim
import com.saifulsandbox.famex.repositories.ExpenseClaimRepository
import com.saifulsandbox.famex.repositories.FamexUserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ExpenseClaimService(val expenseClaimRepository: ExpenseClaimRepository, val famexUserRepository: FamexUserRepository) {

    fun createNewExpenseClaim(amount: Long, name: String): ExpenseClaim {
        val currentUser = famexUserRepository.findByIdOrNull(1)
        val newClaim = ExpenseClaim(name = name, amount = amount, createdAt = Date(), payer = currentUser)
        expenseClaimRepository.save(newClaim)
        return newClaim
    }

    fun getAll(): List<ExpenseClaim> {
        val expenseClaims = expenseClaimRepository.findAll()
        return expenseClaims.filterIsInstance<ExpenseClaim>().takeIf { it.size == expenseClaims.count() } as List<ExpenseClaim>
    }
}