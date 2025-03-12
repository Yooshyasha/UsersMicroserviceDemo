package com.yooshyasha.usersmicroservicedemo.services

import com.yooshyasha.usersmicroservicedemo.dto.UserDTO
import com.yooshyasha.usersmicroservicedemo.entities.User
import com.yooshyasha.usersmicroservicedemo.repos.UserRepo
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepo: UserRepo,
) {
    fun getUsers(): Collection<UserDTO> {
        return userRepo.findAll()
            .stream()
            .map { UserDTO(username = it.username) }
            .toList()
    }

    fun addUser(user: User) {
        userRepo.save(user)
    }

    fun updateUser(user: User) {
        userRepo.save(user)
    }

    fun getUserById(userId: UUID): UserDTO {
        val userEntity = userRepo.findById(userId)

        if (userEntity.isEmpty) {
            throw UsernameNotFoundException("User not found")
        }

        return UserDTO(username = userEntity.get().username)
    }

    fun getUserByUsername(username: String): UserDTO {
        val userEntity = userRepo.findByUserName(username)
            ?: throw UsernameNotFoundException("User not found")

        return UserDTO(username = userEntity.username)
    }

    fun existsByUsername(username: String): Boolean {
        return userRepo.existsByUserName(username)
    }
}