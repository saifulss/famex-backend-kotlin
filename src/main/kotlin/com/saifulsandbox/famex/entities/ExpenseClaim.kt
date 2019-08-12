package com.saifulsandbox.famex.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "expense_claims")
data class ExpenseClaim(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,

        val name: String? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "payer_id")
        val payer: FamexUser? = null,

        val amount: Long? = null,
        val settledAt: Date? = null,
        val createdAt: Date? = null
)