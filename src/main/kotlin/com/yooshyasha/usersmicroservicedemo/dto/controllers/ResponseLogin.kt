package com.yooshyasha.usersmicroservicedemo.dto.controllers

data class ResponseLogin(
    val username: String,
    val jwtToken: String,
)