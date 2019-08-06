package com.example.demo

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("blog")
class DemoProperties {

    lateinit var title: String
    val banner = Banner()

    class Banner {
        var title: String? = null
        lateinit var content: String
    }

}