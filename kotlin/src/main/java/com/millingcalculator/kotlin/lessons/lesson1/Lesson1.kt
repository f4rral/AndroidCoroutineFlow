package com.millingcalculator.kotlin.lessons.lesson1

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.random.Random

val numbers = listOf(3, 4, 8, 16, 5, 7, 11, 32, 41, 28, 43, 47, 84, 116, 53, 59, 61)

fun main() {
    mainSequence()
//    mainFlow()
}

fun mainSequence() {
    println("--- mainSequence start ---")

    val numbersSequence = numbers.asSequence()
    numbersSequence
        .filter { it.isRandom()  }
        .filter { it > 20 }
        .map {
            "Number: $it"
        }
        .forEach { println(it) }

    println("--- mainSequence end ---")
}

suspend fun mainFlow() {
    println("--- mainFlow start ---")

    val numbersFlow = numbers.asFlow()
    numbersFlow
        .filter { it.isPrime() }
        .filter { it > 20 }
        .map { "Number: $it" }
        .collect { println(it) }

    println("--- mainFlow end ---")
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

fun Int.isRandom(): Boolean {
    Thread.sleep(500)
    return Random.nextBoolean()
}