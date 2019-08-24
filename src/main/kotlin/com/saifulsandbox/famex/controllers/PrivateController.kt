package com.saifulsandbox.famex.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/private")
class PrivateController {

    val message: String
        @GetMapping
        get() = "Hello from private API controller"
}
