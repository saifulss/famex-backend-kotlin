package com.saifulsandbox.famex.repositories

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@DataJpaTest
@RunWith(SpringRunner::class)
class FamexUserRepositoryTest {

    @Autowired
    internal var famexUserRepository: FamexUserRepository? = null

    @Test
    fun `it should have no records`() {
        assertEquals(0, famexUserRepository!!.count())
    }
}