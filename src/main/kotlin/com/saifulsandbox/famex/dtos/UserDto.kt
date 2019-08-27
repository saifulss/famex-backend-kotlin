package com.saifulsandbox.famex.dtos

import com.saifulsandbox.famex.entities.User

data class UserDto(
        val id: Long? = null,
        val displayName: String?,
        val email: String
) {
    constructor(user: User) : this(
            user.id,
            user.displayName,
            user.email
    )
}
