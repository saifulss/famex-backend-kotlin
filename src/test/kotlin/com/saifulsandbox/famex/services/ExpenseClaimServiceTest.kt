package com.saifulsandbox.famex.services

import com.saifulsandbox.famex.entities.ExpenseClaim
import com.saifulsandbox.famex.entities.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

@SpringBootTest
@AutoConfigureTestEntityManager
class ExpenseClaimServiceTest {
    @Autowired
    lateinit var expenseClaimService: ExpenseClaimService

    @Autowired
    private lateinit var categoryService: CategoryService

    @Autowired
    private lateinit var fakerService: FakerService

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

    @Test
    fun `it should have UTC0 timezone`() {
        val category = fakerService.createRandomCategory()
        val user = fakerService.createRandomUser()

        // given a newly created expense claim in database
        val expenseClaim = expenseClaimService.save(ExpenseClaim(
                null,
                100,
                category,
                user,
                "xxx",
                null,
                LocalDateTime.of(
                        1970,
                        1,
                        1,
                        0,
                        0,
                        0,
                        0
                )
        ))

        // when we retrieve the contents of the createdAt field
        val createdAt = expenseClaim.createdAt

        // then the contents should be UTC0 timezone
        assertEquals("Coordinated Universal Time", TimeZone.getDefault().displayName)
        assertEquals(createdAt.toString(), "1970-01-01T00:00")
    }
}