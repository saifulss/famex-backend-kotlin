package com.saifulsandbox.famex.controllers

import com.saifulsandbox.famex.entities.ExpenseClaim
import com.saifulsandbox.famex.repositories.ExpenseClaimRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("expense-claims")
class ExpenseClaimController(private val expenseClaimRepository: ExpenseClaimRepository) {

    @GetMapping
    fun index(): MutableIterable<ExpenseClaim> {
        return expenseClaimRepository.findAll()
    }

}