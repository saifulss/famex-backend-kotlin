package com.saifulsandbox.famex.dtofactories

import com.saifulsandbox.famex.dtos.ExpenseClaimDto
import com.saifulsandbox.famex.entities.ExpenseClaim

class ExpenseClaimDtoFactory {
    companion object {
        fun createFromEntity(entity: ExpenseClaim): ExpenseClaimDto = ExpenseClaimDto(
                entity.id,
                entity.name,
                FamexUserDtoFactory.createFromEntity(entity.payer),
                entity.amount,
                entity.settledAt,
                entity.createdAt
        )
    }
}