package com.androidcoroutineflow.lesson2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*


object UsersRepository {

    private val users = mutableListOf("Nick", "John", "Max")

    suspend fun addUser(user: String) {
        delay(10)
        users.add(user)
    }

    suspend fun loadUsers(): Flow<List<String>> {
        return flow {
            while (true) {
                emit(users.toList())
                delay(2000)
            }
        }
    }
}
