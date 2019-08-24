package com.saifulsandbox.famex.dtos

import java.util.*

data class ExpenseClaimDto(
        val id: Long?,
        val name: String?,
        val payer: UserDto?,
        val amount: Long?,
        val settledAt: Date?,
        val createdAt: Date?
)
