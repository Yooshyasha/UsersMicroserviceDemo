package com.yooshyasha.usersmicroservicedemo.controller

import com.yooshyasha.usersmicroservicedemo.dto.controllers.RequestLogin
import com.yooshyasha.usersmicroservicedemo.dto.controllers.RequestRegister
import com.yooshyasha.usersmicroservicedemo.dto.controllers.ResponseLogin
import com.yooshyasha.usersmicroservicedemo.dto.controllers.ResponseRegister
import com.yooshyasha.usersmicroservicedemo.services.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/jwt/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/login")
    fun login(loginData: RequestLogin): ResponseEntity<ResponseLogin> {
        val result = authService.login(loginData.username, loginData.password)

        return ResponseEntity.ok(ResponseLogin(loginData.username, result))
    }

    @PostMapping("/register")
    fun register(registerData: RequestRegister): ResponseEntity<ResponseRegister> {
        val result = authService.register(registerData.username, registerData.password)

        return ResponseEntity.ok(ResponseRegister(registerData.username, result))
    }
}