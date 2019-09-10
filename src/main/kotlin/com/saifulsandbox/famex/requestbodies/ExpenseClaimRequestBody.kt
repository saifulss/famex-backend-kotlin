package com.saifulsandbox.famex.requestbodies

data class ExpenseClaimRequestBody(
        val amount: Long,
        val categoryId: Long,
        val description: String?
)