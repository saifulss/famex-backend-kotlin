package com.saifulsandbox.famex.controllers

import com.saifulsandbox.famex.entities.ExpenseClaim
import com.saifulsandbox.famex.requestbodies.ExpenseClaimRequestBody
import com.saifulsandbox.famex.services.ExpenseClaimService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("expense-claims")
class ExpenseClaimController(private val expenseClaimService: ExpenseClaimService) {

    // index
    // show
    // store
    // update
    // destroy

    @PostMapping
    fun store(@RequestBody requestBody: ExpenseClaimRequestBody): ExpenseClaim {
        System.err.println(requestBody)
        System.err.println(requestBody.amount)
        System.err.println(requestBody.name)

        return expenseClaimService.createNewExpenseClaim(requestBody.amount, requestBody.name)
    }

}