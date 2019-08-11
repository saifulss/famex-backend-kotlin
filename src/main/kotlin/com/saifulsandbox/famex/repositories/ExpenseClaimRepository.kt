package com.saifulsandbox.famex.repositories

import com.saifulsandbox.famex.entities.ExpenseClaim
import org.springframework.data.repository.CrudRepository

interface ExpenseClaimRepository : CrudRepository<ExpenseClaim, Long> {
    override fun count(): Long
}