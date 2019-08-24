package com.saifulsandbox.famex.controllers

import com.saifulsandbox.famex.dtofactories.ExpenseClaimDtoFactory
import com.saifulsandbox.famex.dtos.ExpenseClaimDto
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

    @GetMapping
    fun index(): List<ExpenseClaimDto> = expenseClaimService.getAll().map { ExpenseClaimDtoFactory.createFromEntity(it) }

    @PostMapping
    fun store(@RequestBody requestBody: ExpenseClaimRequestBody): ExpenseClaim {
        System.err.println(requestBody)
        System.err.println(requestBody.amount)
        System.err.println(requestBody.name)

        return expenseClaimService.createNewExpenseClaim(requestBody.amount, requestBody.name)
    }

}