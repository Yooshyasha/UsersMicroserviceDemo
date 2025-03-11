package com.yooshyasha.usersmicroservicedemo.dto.controllers

data class ResponseRegister (
    val username: String,
    val jwtToken: String,
    val refreshToken: String,
)