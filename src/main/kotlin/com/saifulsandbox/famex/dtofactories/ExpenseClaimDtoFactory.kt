package com.saifulsandbox.famex.dtofactories

import com.saifulsandbox.famex.dtos.ExpenseClaimDto
import com.saifulsandbox.famex.entities.ExpenseClaim

class ExpenseClaimDtoFactory {
    companion object {
        fun createFromEntity(entity: ExpenseClaim): ExpenseClaimDto = ExpenseClaimDto(
                entity.id,
                entity.amount,
                CategoryDtoFactory.createFromEntity(entity.category),
                entity.description,
                UserDtoFactory.createFromEntity(entity.payer),
                entity.settledAt,
                entity.createdAt
        )
    }
}