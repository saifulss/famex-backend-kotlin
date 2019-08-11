package com.saifulsandbox.famex.controllers

import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ExpenseClaimControllerTest {

    @Autowired
    private val mvc: MockMvc? = null

    @Test
    fun index_can_return_2_records_in_json_response() {
        val mvcResult = mvc?.perform(get("/expense-claims")
                .contentType(MediaType.APPLICATION_JSON))
                ?.andExpect(status().isOk)
                ?.andReturn()
        System.err.println(mvcResult?.response?.contentAsString)
    }
}