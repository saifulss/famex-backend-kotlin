package com.saifulsandbox.famex.repositories

import com.saifulsandbox.famex.entities.ExpenseClaim
import org.springframework.data.jpa.repository.JpaRepository

interface ExpenseClaimRepository : JpaRepository<ExpenseClaim, Long> {
    override fun count(): Long
}