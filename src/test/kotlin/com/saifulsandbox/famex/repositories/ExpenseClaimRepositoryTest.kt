package com.saifulsandbox.famex.repositories

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
@RunWith(SpringRunner::class)
class ExpenseClaimRepositoryTest {

    @Autowired
    internal var expenseClaimRepository: ExpenseClaimRepository? = null

    @Test
    fun it_should_have_no_records() {
        assertEquals(0, expenseClaimRepository!!.count())
    }
}