package com.yooshyasha.usersmicroservicedemo.repos

import com.yooshyasha.usersmicroservicedemo.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepo : JpaRepository<User, UUID> {
    fun findByUserName(username: String): User?

    fun findById(userId: User): User?
}