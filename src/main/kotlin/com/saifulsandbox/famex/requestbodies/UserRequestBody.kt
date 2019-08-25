package com.saifulsandbox.famex.requestbodies

data class UserRequestBody(
        val email: String,
        val password: String,
        val displayName: String
)