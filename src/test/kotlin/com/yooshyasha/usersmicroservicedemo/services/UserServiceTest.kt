package com.yooshyasha.usersmicroservicedemo.services

import com.yooshyasha.usersmicroservicedemo.repos.UserRepo
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class UserServiceTest {
    @Mock
    private lateinit var userRepo: UserRepo

    @InjectMocks
    private lateinit var userService: UserService

    @Test
    fun getUsers() {
        Mockito.`when`(userRepo.findAll()).thenReturn(listOf())

        userService.getUsers()

        Mockito.verify(userRepo, Mockito.times(1)).findAll()
    }
}