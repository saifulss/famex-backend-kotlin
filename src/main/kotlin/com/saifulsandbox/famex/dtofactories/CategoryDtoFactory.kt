package com.saifulsandbox.famex.dtofactories

import com.saifulsandbox.famex.dtos.CategoryDto
import com.saifulsandbox.famex.entities.Category

class CategoryDtoFactory {
    companion object {
        fun createFromEntity(entity: Category): CategoryDto = CategoryDto(
                entity.id,
                entity.name
        )
    }
}