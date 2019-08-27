package com.saifulsandbox.famex.filters

import com.saifulsandbox.famex.JwtUtils
import com.saifulsandbox.famex.constants.SecurityConstants
import com.saifulsandbox.famex.security.CustomUserDetails
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// This class comes into play when a request comes in giving a username and password, asking for an access token in return.
class JwtAuthenticationFilter(authenticationManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {
    init {
        this.authenticationManager = authenticationManager
        setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL)
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {
        val username = request.getParameter("username")
        val password = request.getParameter("password")
        val authenticationToken = UsernamePasswordAuthenticationToken(username, password)

        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          filterChain: FilterChain,
                                          authentication: Authentication) {
        val customUserDetails = authentication.principal as CustomUserDetails

        val token = JwtUtils().generateToken(customUserDetails)

        response.addHeader(SecurityConstants.TOKEN_HEADER, token)
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, failed: AuthenticationException?) {
        if (failed == null) throw Exception("Authentication attempt failed for unknown reasons.")
        throw failed
    }
}