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
    lateinit var testEntityManager: TestEntityManager

    @Test
    @Transactional
    fun `it can fetch expense claim records from database`() {
        // given 2 expense claim records in database
        val user = User(displayName = "user", email = "xxx@xxx.com", password = "xxx")
        testEntityManager.persistAndFlush(user)
        assertNotNull(user.id)

        expenseClaimService.createNewExpenseClaim(1, "1", user.id!!)
        expenseClaimService.createNewExpenseClaim(2, "2", user.id!!)

        // when we fetch all records
        val records = expenseClaimService.getAll()

        // then we expect to find 2 records
        println(records)
        assertEquals(2, records.size)
    }
}