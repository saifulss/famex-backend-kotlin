package com.saifulsandbox.famex.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/public")
class PublicController {

    val message: String
        @GetMapping
        get() = "Hello from public API controller"
}
