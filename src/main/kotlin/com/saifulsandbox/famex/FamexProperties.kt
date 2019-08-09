package com.saifulsandbox.famex

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("blog")
class FamexProperties {

    lateinit var title: String
    val banner = Banner()

    class Banner {
        var title: String? = null
        lateinit var content: String
    }

}