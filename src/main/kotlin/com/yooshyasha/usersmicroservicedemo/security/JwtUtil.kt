package com.yooshyasha.usersmicroservicedemo.security

import com.yooshyasha.usersmicroservicedemo.services.UserService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil(
    private val userService: UserService,
) {
    private final val SECRET = Keys.hmacShaKeyFor(Base64.getDecoder().decode("1k3A36gt4CmWN6b+wFfY3Q=="))
    private final val TOKEN_LIFETIME = 60 * 60 * 60 * 24

    fun extractUsernameFromToken(token: String): String? {
        return extractAllClaimsFromToken(token).subject
    }

    fun extractAllClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .verifyWith(SECRET)
            .build()
            .parseSignedClaims(token)
            .payload
    }

    fun generateToken(userDetails: UserDetails): String {
        return Jwts.builder()
            .signWith(SECRET)
            .subject(userDetails.username)
            .expiration(Date(System.currentTimeMillis() + TOKEN_LIFETIME))
            .issuedAt(Date())
            .compact()
    }
}