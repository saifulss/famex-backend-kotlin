package com.saifulsandbox.famex.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "expense_claims")
data class ExpenseClaim(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        var name: String? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "payer_id")
        val payer: User,

        var amount: Long,
        val settledAt: Date? = null,
        val createdAt: Date? = null
)