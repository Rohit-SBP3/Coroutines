package flows

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/***
 * Kotlin Flows are part of Kotlin Coroutines, designed to handle asynchronous streams of data.
 * Think of them like a reactive stream that emits values sequentially over time.
 *
 * 🔄 What is a Flow?
 * A Flow<T> is a cold asynchronous stream that can emit multiple values sequentially.
 * “Cold” means it doesn’t start emitting until someone collects it.
 * It’s suspendable, works well with coroutines.
 * It can emit, transform, and collect data over time—perfect for things like network calls, database changes, user input, etc.
 *
 *
 * ✅ Why Use Flow?
 * Structured concurrency – works with coroutines.
 * Backpressure support – unlike LiveData.
 * Functional operators – like map, filter, combine, flatMapLatest, etc.
 * Works well with Room, network layers, repository patterns, and Jetpack Compose.
 *
 * 🔥 Common Operators
 * map {} – transform data.
 * filter {} – conditionally pass data.
 * debounce() – limit emission rate.
 * distinctUntilChanged() – ignore duplicates.
 * catch {} – handle errors gracefully.
 * onEach {} – side effects like logging.
 *
 * Type                Purpose                     Cold/Hot      # of values       Use case
 * Flow           Asynchronous streams               Cold            Many          Room, network, repository
 * LiveData       Lifecycle-aware observable         Hot            Latest         UI updates in MVVM
 * StateFlow      Observable state holder            Hot            Latest         Jetpack Compose / UI state
 * SharedFlow     Broadcast-like data emission       Hot             Many          Events like navigation, Toasts
 *
 * */

fun main(): Unit = runBlocking{

    GlobalScope.launch {
        numbersFlow().collect { value ->
            println("Received $value")
        }
    }.join()
}

fun numbersFlow(): Flow<Int> = flow {
    for (i in 1..5) {
        delay(1000)
        emit(i)
    }
}