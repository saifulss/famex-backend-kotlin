package com.saifulsandbox.famex.entities

import javax.persistence.*

@Entity
@Table(name = "famex_users")
data class FamexUser(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long? = null,

        val displayName: String,

        val email: String,

        val password: String,

        @OneToMany(mappedBy = "payer")
        val expenseClaims: List<ExpenseClaim>? = null
)