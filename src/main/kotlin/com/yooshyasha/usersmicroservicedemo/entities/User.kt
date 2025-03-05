package com.yooshyasha.usersmicroservicedemo.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.UUID) private var id: UUID? = null,

    private var userName: String? = null,
    private var hashedPassword: String? = null,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf()
    }

    override fun getPassword(): String {
        return hashedPassword!!
    }

    override fun getUsername(): String {
        return userName!!
    }
}