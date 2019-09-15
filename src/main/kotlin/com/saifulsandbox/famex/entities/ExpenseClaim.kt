package com.saifulsandbox.famex.entities

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "expense_claims")
data class ExpenseClaim(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        var amount: Long,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id")
        var category: Category,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "payer_id")
        val payer: User,

        var description: String? = null,

        val settledAt: LocalDateTime? = null,

        @CreatedDate
        val createdAt: LocalDateTime
)