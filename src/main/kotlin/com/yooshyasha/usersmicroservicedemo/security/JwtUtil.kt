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
    private final val SECRET = Keys.hmacShaKeyFor(
        Base64.getDecoder()
            .decode("HchXPor/fZ0G6nYLCGSq0oNTjGzJrF7DCVvjNvKiPgO4blCnss54b5Gpgw7T5L31RYvKaz3t67fFWxrDecm44A==")
    )
    private final val TOKEN_LIFETIME = 1000 * 60 * 60 * 24

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

    fun generateToken(userDetails: UserDetails, isRefreshToken: Boolean = false): String {
        val now = Date()
        val expiration = Date(now.time + if (isRefreshToken) TOKEN_LIFETIME else TOKEN_LIFETIME * 7)

        return Jwts.builder()
            .signWith(SECRET)
            .subject(userDetails.username)
            .expiration(expiration)
            .issuedAt(now)
            .compact()
    }

    fun verifyToken(token: String, userDetails: UserDetails): Boolean {
        val claims = extractAllClaimsFromToken(token)

        return claims.subject != userDetails.username && !claims.expiration.before(Date())
    }
}