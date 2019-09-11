package com.saifulsandbox.famex

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(FamexProperties::class)
class FamexApplication

fun main(args: Array<String>) {
    runApplication<FamexApplication>(*args)
}
