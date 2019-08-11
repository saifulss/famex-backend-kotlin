package com.saifulsandbox.famex.entities

import java.util.*
import javax.persistence.*

@Entity
data class ExpenseClaim(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        val name: String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "payer_id")
        val payer: FamexUser,

        val amount: Long,

        val settledAt: Date
)