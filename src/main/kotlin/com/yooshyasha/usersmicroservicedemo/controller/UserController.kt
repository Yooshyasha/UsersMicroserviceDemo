package com.yooshyasha.usersmicroservicedemo.controller

import com.yooshyasha.usersmicroservicedemo.entities.User
import com.yooshyasha.usersmicroservicedemo.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/me")
    fun getMe(): ResponseEntity<User?> {
        val username = SecurityContextHolder.getContext().authentication.name
        val user = userService.getUserByUsername(username)
        return ResponseEntity.ok(user)
    }

    @GetMapping("/getAllUsers")
    fun getAllUsers(): ResponseEntity<Collection<User>> {
        val users = userService.getUsers()

        return ResponseEntity.ok(users)
    }

    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable("id") id: String): ResponseEntity<Optional<User>> {
        val uuid = UUID.fromString(id)

        val user = userService.getUserById(uuid)

        return ResponseEntity.ok(user)
    }
}