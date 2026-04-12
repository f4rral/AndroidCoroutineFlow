package com.millingcalculator.kotlin.lessons.lesson3

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*


val numbers = listOf(3, 4, 8, 16, 5, 7, 11, 32, 41, 28, 43, 47, 84, 116, 53, 59, 61)

suspend fun main() {
    mainFlow()
}

suspend fun mainFlow() {
    println("--- mainFlow start ---")

    val result = getFlowByCustom()
        .filter { it.isPrime() }
        .filter { it > 20 }
        .map {
            println("Map")
            "Number: $it"
        }
        .collect { println(it) }
//        .toList()
//        .first()
//        .last()

    println(result)

    println("--- mainFlow end ---")
}

fun getFlowByFlowOfBuilder(): Flow<Int> {
    return flowOf(3, 4, 8, 16, 5, 7, 11, 32, 41, 28, 43, 47, 84, 116, 53, 59, 61)
}

fun getFlowByCustom(): Flow<Int> {
    val fistFlow = getFlowByFlowOfBuilder()

    return flow {
        // Добавление через цикл
//        fistFlow.collect {
//            println("Emitted from fist flow: $it")
//            emit(it)
//        }

        // Добавление всего
        emitAll(fistFlow)

        // Бесконечное добавление
//        var i = 0
//        while(true) {
//            emit(i++)
//        }
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