package com.millingcalculator.kotlin.lessons.lesson2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*


val numbers = listOf(3, 4, 8, 16, 5, 7, 11, 32, 41, 28, 43, 47, 84, 116, 53, 59, 61)

suspend fun main() {
    mainFlow()
}

suspend fun mainFlow() {
    println("--- mainFlow start ---")

    getFlowByCustom()
        .filter { it.isPrime() }
        .filter { it > 20 }
        .map { "Number: $it" }
        .collect { println(it) }

    println("--- mainFlow end ---")
}

fun getFlowByFlowOfBuilder(): Flow<Int> {
    return flowOf(3, 4, 8, 16, 5, 7, 11, 32, 41, 28, 43, 47, 84, 116, 53, 59, 61)
}

fun getFlowByCustom(): Flow<Int> {
    return flow {
//        val a = 43
//        emit(a)
//        println("Emitted $a")
//
//        delay(1000)
//
//        val b = a * 10
//        emit(b)
//        println("Emitted $b")

        numbers.forEach {
            emit(it)
        }
    }
}

suspend fun Int.isPrime(): Boolean {
    if (this <= 1) {
        return false
    }

    for (i in 2..this / 2) {
        delay(50)

        if (this % i == 0) {
            return false
        }
    }

    return true
}