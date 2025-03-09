package com.yooshyasha.usersmicroservicedemo.entities

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
@Table(name = "\"users\"")
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