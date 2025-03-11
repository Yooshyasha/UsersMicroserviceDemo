package com.yooshyasha.usersmicroservicedemo.services

import com.yooshyasha.usersmicroservicedemo.entities.User
import com.yooshyasha.usersmicroservicedemo.security.JwtUtil
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.naming.AuthenticationException

@Service
class AuthService(
    private val jwtUtil: JwtUtil,
    private val userService: UserService,
    private val passwordEncoder: BCryptPasswordEncoder,
) {
    fun login(username: String, password: String): String {
        if (!userService.existsByUsername(username)) {
            throw UsernameNotFoundException("Username $username not found")
        }

        val user = userService.getUserByUsername(username)

        if (!passwordEncoder.matches(password, user!!.password)) {
            throw AuthenticationException("Wrong password")
        }

        return jwtUtil.generateToken(user)
    }

    fun register(username: String, password: String): String {
        if (userService.existsByUsername(username)) {
            throw UsernameNotFoundException("Username $username already taken")
        }

        val hashedPassword = passwordEncoder.encode(password)

        val user = User(userName = username, hashedPassword = hashedPassword)

        userService.addUser(user)

        return jwtUtil.generateToken(user)
    }

    fun loginWithRefreshToken(token: String): String {
        val username = jwtUtil.extractUsernameFromToken(token)
        val user = userService.getUserByUsername(username!!)
            ?: throw UsernameNotFoundException("Username $username not found")

        return jwtUtil.generateToken(user)
    }

    fun generateRefreshToken(token: String): String {
        val username = jwtUtil.extractUsernameFromToken(token)
        val user = userService.getUserByUsername(username!!)
            ?: throw UsernameNotFoundException("Username $username not found")

        return jwtUtil.generateToken(user, true)
    }
}