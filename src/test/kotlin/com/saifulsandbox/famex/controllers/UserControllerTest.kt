package com.saifulsandbox.famex.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.saifulsandbox.famex.requestbodies.UserRequestBody
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
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

    @Test
    @WithMockUser
    fun `it can fetch existing users`() {
        val mvcResult = mvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk).andReturn()
        System.err.println(mvcResult.response.contentAsString)
    }

    @Test
    fun `it can create a new user while not authenticated`() {
        val userRequestBody = UserRequestBody("email1", "password1", "displayName1")
        val objectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()

        val mvcResult = mvc.perform(post("/api/users")
                .content(objectWriter.writeValueAsString(userRequestBody))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk).andReturn()
        System.err.println(mvcResult.response.contentAsString)
    }

    @Test
    fun `it does not allow fetching users if not authenticated`() {
        mvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden)
    }
}