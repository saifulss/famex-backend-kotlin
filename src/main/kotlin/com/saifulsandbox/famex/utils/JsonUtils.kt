package com.saifulsandbox.famex.utils

import com.fasterxml.jackson.databind.ObjectMapper

fun toJson(pojo: Any): String {
    val objectWriter = ObjectMapper().writer().withDefaultPrettyPrinter()
    return objectWriter.writeValueAsString(pojo)
}