package com.saifulsandbox.famex.filters

import com.saifulsandbox.famex.JwtUtils
import com.saifulsandbox.famex.constants.SecurityConstants
import com.saifulsandbox.famex.dtofactories.UserDtoFactory
import com.saifulsandbox.famex.dtos.AccessTokenDto
import com.saifulsandbox.famex.requestbodies.AuthenticationRequestBody
import com.saifulsandbox.famex.security.CustomUserDetails
import com.saifulsandbox.famex.utils.toJson
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.stream.Collectors
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
        val requestBody = request.reader.lines().collect(Collectors.joining(System.lineSeparator()))
        val authenticationRequestBody = AuthenticationRequestBody.fromJson(requestBody)

        val authenticationToken = UsernamePasswordAuthenticationToken(
                authenticationRequestBody.username,
                authenticationRequestBody.password
        )

        return authenticationManager.authenticate(authenticationToken)
    }

    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          filterChain: FilterChain,
                                          authentication: Authentication) {

        val customUserDetails = authentication.principal as CustomUserDetails

        val accessToken = JwtUtils().generateToken(customUserDetails)

        val accessTokenDto = AccessTokenDto(UserDtoFactory.createFromEntity(customUserDetails.user), accessToken)

        response.addHeader("Content-Type", "application/json")
        response.writer.append(toJson(accessTokenDto))    // TODO: should move this out into a proper auth controller
    }

    override fun unsuccessfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?, failed: AuthenticationException?) {
        if (failed == null) throw Exception("Authentication attempt failed for unknown reasons.")
        throw failed
    }
}