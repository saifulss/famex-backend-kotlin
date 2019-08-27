package com.saifulsandbox.famex.controllers

import com.saifulsandbox.famex.TestAuthUtils
import com.saifulsandbox.famex.requestbodies.UserRequestBody
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

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var testAuthUtils: TestAuthUtils

    @Test
    fun `it can fetch existing users`() {
        val mvcResult = mvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", testAuthUtils.getQuickToken()))
                .andExpect(status().isOk).andReturn()
        System.err.println(mvcResult.response.contentAsString)
    }

    @Test
    fun `it can create a new user while not authenticated`() {
        val userRequestBody = UserRequestBody("email1", "password1", "displayName1")

        val mvcResult = mvc.perform(post("/api/users")
                .content(toJson(userRequestBody))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", testAuthUtils.getQuickToken()))
                .andExpect(status().isOk).andReturn()
        System.err.println(mvcResult.response.contentAsString)
    }

    @Test
    fun `it does not allow fetching users if not authenticated`() {
        mvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden)
    }
}