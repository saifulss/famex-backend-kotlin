package com.saifulsandbox.famex

import com.saifulsandbox.famex.constants.SecurityConstants
import com.saifulsandbox.famex.security.CustomUserDetails
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class JwtUtils {
    fun generateToken(customUserDetails: CustomUserDetails): String {
        val signingKey = SecurityConstants.JWT_SECRET.toByteArray()

        val roles = customUserDetails.authorities
                .stream()
                .map { it.authority }
                .collect(Collectors.toList())

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(signingKey), SignatureAlgorithm.HS512)
                .setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
                .setIssuer(SecurityConstants.TOKEN_ISSUER)
                .setAudience(SecurityConstants.TOKEN_AUDIENCE)
                .setSubject(customUserDetails.user.email)   // we load the user's email as the JWT's subject
                .setExpiration(Date(System.currentTimeMillis() + 864000000))
                .claim("rol", roles)
                .compact()
    }
}