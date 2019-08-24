package com.saifulsandbox.famex.dtofactories

import com.saifulsandbox.famex.dtos.FamexUserDto
import com.saifulsandbox.famex.entities.FamexUser

class FamexUserDtoFactory {
    companion object {
        fun createFromEntity(entity: FamexUser?): FamexUserDto = FamexUserDto(
                entity?.id,
                entity?.displayName,
                entity?.email
        )
    }
}