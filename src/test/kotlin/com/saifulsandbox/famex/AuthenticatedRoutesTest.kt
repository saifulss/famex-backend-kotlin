package com.saifulsandbox.famex

import com.saifulsandbox.famex.constants.SecurityConstants
import com.saifulsandbox.famex.requestbodies.AuthenticationRequestBody
import com.saifulsandbox.famex.services.UserService
import com.saifulsandbox.famex.utils.toJson
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticatedRoutesTest {

    @Autowired
    lateinit var userService: UserService

    @Autowired
    private lateinit var mvc: MockMvc

    private lateinit var protectedRequestBuilder: MockHttpServletRequestBuilder
    private lateinit var authRequestBuilder: MockHttpServletRequestBuilder

    @BeforeEach
    fun setUp() {
        protectedRequestBuilder = get("/expense-claims")    // Arbitrarily chosen route that's known to be protected
    }

    @Test
    fun `it cannot access protected routes when not supplying authentication`() {
        mvc.perform(protectedRequestBuilder
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden)
    }

    @Test
    fun `it can authenticate to get JWT access token`() {
        userService.createNewUser("user@example.com", "secret", "user")

        val authenticationRequestBody = AuthenticationRequestBody("user@example.com", "secret")
        val json = toJson(authenticationRequestBody)

        mvc.perform(post(SecurityConstants.AUTH_LOGIN_URL)
                .content(json))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.user.email", `is`("user@example.com")))
                .andExpect(jsonPath("$.accessToken").value(notNullValue()))
                .andReturn()
    }
}