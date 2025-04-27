package flows

import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*

fun main() = runBlocking{

    // Above flow commands on the provided context and below main on the main...
    // flowon sees(works) upstream..

    launch {
        producerNumbers()
            .map {
                delay(1000)
                println("Map Thread" + Thread.currentThread().name)
                it * 2
            }
            .flowOn(Dispatchers.IO)
            .filter {
                delay(500)
                println("Filter Thread" + Thread.currentThread().name)
                it >= 4
            }
            .flowOn(Dispatchers.Unconfined)
            .collect{ println("Your number is: $it") }
        println("Main Thread" + Thread.currentThread().name)
    }

    println("Main is Game!!!")
}

fun producerNumbers(): Flow<Int> = flow{
        val noList = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        noList.forEach {
            delay(1000)
            println("Produce Thread: " + Thread.currentThread().name)
            emit(it)
        }
}