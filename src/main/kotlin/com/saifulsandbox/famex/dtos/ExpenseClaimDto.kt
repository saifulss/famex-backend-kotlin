package com.saifulsandbox.famex.dtos

import com.saifulsandbox.famex.dtofactories.CategoryDtoFactory
import com.saifulsandbox.famex.dtofactories.UserDtoFactory
import com.saifulsandbox.famex.entities.ExpenseClaim
import java.util.*

data class ExpenseClaimDto(
        val id: Long?,
        val amount: Long?,
        val category: CategoryDto,
        val description: String?,
        val payer: UserDto?,
        val settledAt: Date?,
        val createdAt: Date?
) {
    constructor(expenseClaim: ExpenseClaim) : this(
            expenseClaim.id,
            expenseClaim.amount,
            CategoryDtoFactory.createFromEntity(expenseClaim.category),
            expenseClaim.description,
            UserDtoFactory.createFromEntity(expenseClaim.payer),
            expenseClaim.settledAt,
            expenseClaim.createdAt
    )
}
