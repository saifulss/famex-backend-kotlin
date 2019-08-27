package com.saifulsandbox.famex.controllers

import com.saifulsandbox.famex.TestAuthUtils
import com.saifulsandbox.famex.requestbodies.ExpenseClaimRequestBody
import com.saifulsandbox.famex.utils.toJson
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import javax.transaction.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ExpenseClaimControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var testAuthUtils: TestAuthUtils

    @Test
    fun `it can fetch all expense claims`() {
        val mvcResult = mvc.perform(get("/expense-claims")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", testAuthUtils.getQuickToken()))
                .andExpect(status().isOk).andReturn()
        System.err.println(mvcResult.response.contentAsString)
    }

    @Test
    @Transactional
    fun `it can create a new expense claim`() {
        val expenseClaimRequestBody = ExpenseClaimRequestBody(1000, "cab")

        val mvcResult = mvc.perform(post("/expense-claims")
                .content(toJson(expenseClaimRequestBody))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", testAuthUtils.getQuickToken()))
                .andExpect(status().isOk).andReturn()
        System.err.println(mvcResult.response.contentAsString)
    }
}