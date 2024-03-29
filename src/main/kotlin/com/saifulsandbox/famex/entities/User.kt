package com.saifulsandbox.famex.entities

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val displayName: String,

        val email: String,

        val password: String,

        @OneToMany(mappedBy = "payer")
        val expenseClaims: List<ExpenseClaim>? = null
)