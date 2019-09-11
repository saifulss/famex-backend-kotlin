package com.saifulsandbox.famex.services

import com.saifulsandbox.famex.repositories.CategoryRepository
import com.saifulsandbox.famex.repositories.ExpenseClaimRepository
import com.saifulsandbox.famex.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class DatabaseService(
        private val categoryRepository: CategoryRepository,
        private val userRepository: UserRepository,
        private val expenseClaimRepository: ExpenseClaimRepository
) {
    fun areAllTablesEmpty(): Boolean {
        var areAllTablesEmpty = true
        if (categoryRepository.count() != 0L) areAllTablesEmpty = false
        if (userRepository.count() != 0L) areAllTablesEmpty = false
        if (expenseClaimRepository.count() != 0L) areAllTablesEmpty = false
        return areAllTablesEmpty
    }
}