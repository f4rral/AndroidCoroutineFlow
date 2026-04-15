package com.millingcalculator.kotlin.lessons.lesson8

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/*
    Cold Flow:
        1. Пока нет подписки flow не выполняется
        2. На каждую подписку создаётся новый экземпляр потока данных
        3. Если подписчикам больше не нужны данные - flow прекращает свою работу
*/

val coroutineScope = CoroutineScope(Dispatchers.IO)

suspend fun main() {
    val flow = getColdFlow()

    val job1 = coroutineScope.launch {
        flow.collect {
            println("Collect 1 $it")
        }
    }

    delay(5000)

    val job2 = coroutineScope.launch {
        flow.collect {
            println("Collect 2 $it")
        }
    }

    job1.join()
    job2.join()
}

fun getColdFlow(): Flow<Int> = flow {
    repeat(100) {
        println("Emitted: $it")
        emit(it)
        delay(1000)
    }
}
