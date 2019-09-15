package com.saifulsandbox.famex

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableConfigurationProperties(FamexProperties::class)
class FamexApplication {
    @PostConstruct
    fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
        println("TimeZone is: ${TimeZone.getDefault().displayName}")
    }
}

fun main(args: Array<String>) {
    runApplication<FamexApplication>(*args)
}