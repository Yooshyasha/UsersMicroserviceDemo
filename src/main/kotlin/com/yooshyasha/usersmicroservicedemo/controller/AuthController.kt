package com.yooshyasha.usersmicroservicedemo.controller

import com.yooshyasha.usersmicroservicedemo.dto.controllers.*
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
        val loginResult = authService.login(loginData.username, loginData.password)
        val refreshToken = authService.generateRefreshToken(loginResult)

        val result = ResponseLogin(
            loginData.username,
            loginResult,
            refreshToken
        )

        return ResponseEntity.ok(result)
    }

    @PostMapping("/register")
    fun register(registerData: RequestRegister): ResponseEntity<ResponseRegister> {
        val registerResult = authService.register(registerData.username, registerData.password)
        val refreshToken = authService.generateRefreshToken(registerResult)

        val result = ResponseRegister(
            registerData.username,
            registerResult,
            refreshToken
        )

        return ResponseEntity.ok(result)
    }

    @PostMapping("/refresh")
    fun refresh(refreshData: RequestRefresh): ResponseEntity<ResponseRefresh> {
        val refreshResult = authService.loginWithRefreshToken(refreshData.refreshToken)

        val result = ResponseRefresh(
            refreshResult
        )

        return ResponseEntity.ok(result)
    }
}