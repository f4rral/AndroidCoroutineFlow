package com.millingcalculator.kotlin.lessons.lesson14

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/*
SharedFlow:
1. Значение по умолчанию - нет
2. Параметр replay - 0, можно изменить
3. Поддержка backpressure - можно устанавливать размер буфера
   и поведение при переполнении (по умолчанию Suspended)
4. При эмите одинаковых элементов - collect() отработает один раз только если установить
   distinctUntilChanged()
5. Свойство value - нет

StateFlow:
1. Значение по умолчанию - есть
2. Параметр replay - 1, изменить нельзя
3. Поддержка backpressure - DropOldest
4. При эмите одинаковых элементов - collect() отработает один раз
5. Свойство value - есть
*/

suspend fun main() {
//    runSharedFlow()
    runStateFlow()
}


suspend fun runSharedFlow() {
    val scope = CoroutineScope(Dispatchers.Default)

    val sharedFlow = MutableSharedFlow<Int>()

    val producer = scope.launch {
        delay(500)

        repeat(10) {
            println("Emitted: $it")
            sharedFlow.emit(it)
            println("After emit: $it")
            delay(200)
        }
    }

    val consumer = scope.launch {
        sharedFlow.collect {
            println("Collected: $it")
            delay(1000)
        }
    }

    producer.join()
    consumer.join()
}

suspend fun runStateFlow() {
    val scope = CoroutineScope(Dispatchers.Default)

    val stateFlow = MutableStateFlow(0)

    val producer = scope.launch {
        delay(500)

        repeat(10) {
            println("Emitted: $it")
            stateFlow.emit(it)
            println("After emit: $it")
            delay(200)
        }
    }

    val consumer = scope.launch {
        stateFlow.collectLatest {
            println("Collected start: $it")
            delay(5000)
            println("Collected end: $it")
        }
    }

    producer.join()
    consumer.join()
}
