package com.saifulsandbox.famex.entities

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class ExpenseClaimTest {
    @Autowired
    lateinit var testEntityManager: TestEntityManager

    @Test
    fun `it can fetch a record`() {
        val user = User(displayName = "user", email = "xxx@xxx.com", password = "xxx")
        testEntityManager.persistAndFlush(user)

        // given that the database has an expense claim record with the name "Taxi"
        val expenseClaim = ExpenseClaim(name = "Taxi", amount = 10000, payer = user)
        testEntityManager.persistAndFlush(expenseClaim)
        assertNotNull(expenseClaim.id)

        // when we fetch the record
        val fetched = testEntityManager.find(ExpenseClaim::class.java, expenseClaim.id)

        // then we should get a record with an assigned value for ID
        assertNotNull(fetched.id)
        assertEquals(expenseClaim.id, fetched.id)
    }
}