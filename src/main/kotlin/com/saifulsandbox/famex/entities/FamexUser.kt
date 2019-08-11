package com.saifulsandbox.famex.entities

import javax.persistence.*

@Entity
data class FamexUser(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long,

        val displayName: String,

        val email: String,

        val password: String,

        @OneToMany(mappedBy = "payer")
        val expenseClaims: List<ExpenseClaim>
)