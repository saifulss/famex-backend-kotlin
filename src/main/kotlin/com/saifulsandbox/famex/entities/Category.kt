package com.saifulsandbox.famex.entities

import javax.persistence.*

@Entity
@Table(name = "categories")
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        val name: String,

        @OneToMany(mappedBy = "payer")
        val expenseClaims: List<ExpenseClaim>? = null
)