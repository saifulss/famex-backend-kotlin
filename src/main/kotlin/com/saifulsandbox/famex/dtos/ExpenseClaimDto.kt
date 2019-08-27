package com.saifulsandbox.famex.dtos

import com.saifulsandbox.famex.dtofactories.UserDtoFactory
import com.saifulsandbox.famex.entities.ExpenseClaim
import java.util.*

data class ExpenseClaimDto(
        val id: Long?,
        val name: String?,
        val payer: UserDto?,
        val amount: Long?,
        val settledAt: Date?,
        val createdAt: Date?
) {
    constructor(expenseClaim: ExpenseClaim) : this(
            null,
            expenseClaim.name,
            UserDtoFactory.createFromEntity(expenseClaim.payer),
            expenseClaim.amount,
            expenseClaim.settledAt,
            expenseClaim.createdAt
    )
}
