package com.saifulsandbox.famex.controllers

import com.saifulsandbox.famex.dtofactories.ExpenseClaimDtoFactory
import com.saifulsandbox.famex.dtos.ExpenseClaimDto
import com.saifulsandbox.famex.requestbodies.ExpenseClaimRequestBody
import com.saifulsandbox.famex.services.ExpenseClaimService
import com.saifulsandbox.famex.springutils.AuthUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/expense-claims")
class ExpenseClaimController(private val expenseClaimService: ExpenseClaimService) {

    @Autowired
    lateinit var authUtils: AuthUtils

    // index
    // show
    // store
    // update
    // destroy

    @GetMapping
    fun index(): List<ExpenseClaimDto> = expenseClaimService.getAll().map { ExpenseClaimDtoFactory.createFromEntity(it) }

    @PostMapping
    fun store(@RequestBody requestBody: ExpenseClaimRequestBody): ExpenseClaimDto {
        val currentUserId = authUtils.getCurrentUser().id ?: throw Exception("Couldn't find ID from current uer.")
        val expenseClaim = expenseClaimService.createNewExpenseClaim(requestBody.amount, requestBody.name, currentUserId)
        return ExpenseClaimDto(expenseClaim)
    }

    @PutMapping("/{id}")
    fun update(
            @RequestBody requestBody: ExpenseClaimRequestBody,
            @PathVariable(value = "id") expenseClaimId: Long
    ): ExpenseClaimDto {
        val expenseClaim = expenseClaimService.getById(expenseClaimId)

        expenseClaim.name = requestBody.name
        expenseClaim.amount = requestBody.amount
        val saved = expenseClaimService.save(expenseClaim)

        return ExpenseClaimDtoFactory.createFromEntity(saved)
    }
}