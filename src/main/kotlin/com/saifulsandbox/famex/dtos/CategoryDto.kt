package com.saifulsandbox.famex.dtos

import com.saifulsandbox.famex.entities.Category

data class CategoryDto(
        val id: Long? = null,
        val name: String
) {
    constructor(category: Category) : this(
            category.id,
            category.name
    )
}
