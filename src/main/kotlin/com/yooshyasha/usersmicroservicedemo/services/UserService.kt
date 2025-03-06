package com.yooshyasha.usersmicroservicedemo.services

import com.yooshyasha.usersmicroservicedemo.entities.User
import com.yooshyasha.usersmicroservicedemo.repos.UserRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepo: UserRepo,
) {
    fun getUsers(): Collection<User> {
        return userRepo.findAll()
    }

    fun addUser(user: User) {
        userRepo.save(user)
    }

    fun updateUser(user: User) {
        userRepo.save(user)
    }

    fun getUserById(userId: UUID): Optional<User> {
        return userRepo.findById(userId)
    }

    fun getUserByUsername(username: String): User? {
        return userRepo.findByUserName(username)
    }

    fun existsByUsername(username: String): Boolean {
        return userRepo.existsByUsername(username)
    }
}