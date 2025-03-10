package com.yooshyasha.usersmicroservicedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableDiscoveryClient
class UsersMicroserviceDemoApplication

fun main(args: Array<String>) {
    runApplication<UsersMicroserviceDemoApplication>(*args)
}
