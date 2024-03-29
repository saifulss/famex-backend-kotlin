package com.saifulsandbox.famex.filters

import com.saifulsandbox.famex.constants.SecurityConstants
import com.saifulsandbox.famex.dtofactories.UserDtoFactory
import com.saifulsandbox.famex.services.UserService
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import java.security.SignatureException
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// This class comes into play when a request comes in supplying an access token. It decides what we load into Spring Security context.
class JwtAuthorizationFilter(authenticationManager: AuthenticationManager, private val userService: UserService) : BasicAuthenticationFilter(authenticationManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  filterChain: FilterChain) {

        val authentication = getAuthentication(request)

        if (authentication == null) {
            filterChain.doFilter(request, response)
            return
        }

        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(SecurityConstants.TOKEN_HEADER)
        if (StringUtils.isNotEmpty(token) && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            try {
                val signingKey = SecurityConstants.JWT_SECRET.toByteArray()

                val parsedToken = Jwts.parser()
                        .setSigningKey(signingKey)
                        .parseClaimsJws(token.replace("Bearer ", ""))

                val userEmailFromJwt = parsedToken.body.subject

                val retrievedUser = userService.getByEmail(userEmailFromJwt).first()
                        ?: throw Exception("No user found.")

                val authorities = (parsedToken.body["rol"] as List<*>).stream()
                        .map { authority -> SimpleGrantedAuthority(authority as String) }
                        .collect(Collectors.toList())

                if (StringUtils.isNotEmpty(userEmailFromJwt)) {
                    return UsernamePasswordAuthenticationToken(UserDtoFactory.createFromEntity(retrievedUser), null, authorities)
                }
            } catch (exception: ExpiredJwtException) {
                log.warn("Request to parse expired JWT : {} failed : {}", token, exception.message)
            } catch (exception: UnsupportedJwtException) {
                log.warn("Request to parse unsupported JWT : {} failed : {}", token, exception.message)
            } catch (exception: MalformedJwtException) {
                log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.message)
            } catch (exception: SignatureException) {
                log.warn("Request to parse JWT with invalid signature : {} failed : {}", token, exception.message)
            } catch (exception: IllegalArgumentException) {
                log.warn("Request to parse empty or null JWT : {} failed : {}", token, exception.message)
            }

        }

        return null
    }

    companion object {

        private val log = LoggerFactory.getLogger(JwtAuthorizationFilter::class.java)
    }
}