package com.maaxgr.repository

interface UserRepository {

    fun getUser(username: String, password: String): User?

    data class User(
        val userId: Int,
        val username: String
    )

}