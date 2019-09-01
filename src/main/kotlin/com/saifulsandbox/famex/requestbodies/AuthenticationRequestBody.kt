package com.saifulsandbox.famex.requestbodies

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper

@JsonIgnoreProperties(ignoreUnknown = true) // useful for when you deserialize and encounter an unknown field
data class AuthenticationRequestBody(
        @JsonProperty("username")   // this is how you override with a custom JSON property name
        val username: String,
        @JsonProperty("password")   // this is how you override with a custom JSON property name
        val password: String
) {
    companion object {
        fun fromJson(jsonString: String): AuthenticationRequestBody {
            if (jsonString.isEmpty()) throw Exception("JSON string is empty.")
            return ObjectMapper().readValue(jsonString, AuthenticationRequestBody::class.java)
        }
    }
}
