package com.saifulsandbox.famex.dtofactories

import com.saifulsandbox.famex.dtos.UserDto
import com.saifulsandbox.famex.entities.User

class UserDtoFactory {
    companion object {
        fun createFromEntity(entity: User?): UserDto = UserDto(
                entity?.id,
                entity?.displayName,
                entity?.email
        )
    }
}