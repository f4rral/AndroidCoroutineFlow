package com.millingcalculator.kotlin.lessons.lesson8

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/*
Cold Flow:
    1. Эмитит значения только если на них кто-то подписался
    2. При каждой подписке создаётся новый поток данных
    3. Подписчикам больше не нужны данные - поток завершает работу
    4. В потоке больше нет данных - завершает свою работу
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
