package com.saifulsandbox.famex.services

import com.saifulsandbox.famex.entities.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import javax.transaction.Transactional

@SpringBootTest
@AutoConfigureTestEntityManager
class ExpenseClaimServiceTest {
    @Autowired
    lateinit var expenseClaimService: ExpenseClaimService

    @Autowired
    private lateinit var categoryService: CategoryService

    @Autowired
    lateinit var testEntityManager: TestEntityManager

    @Test
    @Transactional
    fun `it can fetch expense claim records from database`() {
        // given n expense claim records in database
        val numRecords = expenseClaimService.getAll().size

        val user = User(displayName = "user", email = "xxx@xxx.com", password = "xxx")
        testEntityManager.persistAndFlush(user)
        assertNotNull(user.id)

        val category = categoryService.createNewCategory("xxx")

        expenseClaimService.createNewExpenseClaim(1, category.id!!, null, user.id!!)
        expenseClaimService.createNewExpenseClaim(2, category.id!!, null, user.id!!)

        // when we fetch all records
        val records = expenseClaimService.getAll()

        // then we expect to find n+2 records
        assertEquals(numRecords + 2, records.size)
    }
}