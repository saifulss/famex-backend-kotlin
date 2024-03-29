package com.saifulsandbox.famex.constants

class SecurityConstants private constructor() {
    init {
        throw IllegalStateException("Cannot create instance of static util class")
    }

    companion object {
        const val AUTH_LOGIN_URL = "/api/authenticate"

        // Signing key for HS512 algorithm
        // You can use the page http://www.allkeysgenerator.com/ to generate all kinds of keys
        const val JWT_SECRET = "n2r5u8x/A%D*G-KaPdSgVkYp3s6v9y\$B&E(H+MbQeThWmZq4t7w!z%C*F-J@NcRf"

        // JWT token defaults
        const val TOKEN_HEADER = "Authorization"
        const val TOKEN_PREFIX = "Bearer "
        const val TOKEN_TYPE = "JWT"
        const val TOKEN_ISSUER = "secure-api"
        const val TOKEN_AUDIENCE = "secure-app"
    }
}