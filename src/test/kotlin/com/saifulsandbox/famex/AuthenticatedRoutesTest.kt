package com.saifulsandbox.famex

import com.saifulsandbox.famex.constants.SecurityConstants
import org.hamcrest.core.StringStartsWith
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.header
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticatedRoutesTest {

    @Autowired
    private lateinit var mvc: MockMvc
    private lateinit var protectedRequestBuilder: MockHttpServletRequestBuilder
    private lateinit var authRequestBuilder: MockHttpServletRequestBuilder

    @BeforeEach
    fun setUp() {
        protectedRequestBuilder = get("/expense-claims")    // Arbitrarily chosen route that's known to be protected

        authRequestBuilder = get(SecurityConstants.AUTH_LOGIN_URL)
                .param("username", "someUser")
                .param("password", "somePassword")
    }

    @Test
    fun `it cannot access protected routes when not supplying authentication`() {
        mvc.perform(protectedRequestBuilder
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden)
    }

    @Test
    fun `it can authenticate to get JWT access token and use JWT access token to access protected routes`() {
        val mvcResult = mvc.perform(authRequestBuilder)
                .andExpect(status().isOk)
                .andExpect(header().exists("Authorization"))
                .andExpect(header().string("Authorization", StringStartsWith.startsWith(SecurityConstants.TOKEN_PREFIX)))
                .andReturn()

        val accessTokenWithBearerPrefix = mvcResult.response.getHeaderValue("Authorization") as String

        mvc.perform(protectedRequestBuilder
                .header("Authorization", accessTokenWithBearerPrefix))
                .andExpect(status().isOk)
    }
}