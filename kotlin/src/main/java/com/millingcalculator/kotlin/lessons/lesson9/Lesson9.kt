package com.millingcalculator.kotlin.lessons.lesson9

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/*
Hot Flow:
    1. Эмитит значения независимо от наличия подписки
    2. При каждой подписке подписчики получают одни и те же данные
    3. Подписчикам больше не нужны данные - поток продолжает эмитить значения
    4. В потоке больше нет данных - не завершается никогда
*/

val coroutineScope = CoroutineScope(Dispatchers.IO)

suspend fun main() {
    val flow = MutableSharedFlow<Int>()

    coroutineScope.launch {
        repeat(100) {
            println("Emitted: $it")
            flow.emit(it)
            delay(1000)
        }
    }

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
