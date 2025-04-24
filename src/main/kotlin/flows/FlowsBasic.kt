package flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

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

/***
Kotlin has asynchronous stream support using channels and flows.
Channels(Send & Receive), Channels are hot(means constantly produce whether consumer is consuming or not),
in hot you will not able to get the data you lost.
Flows(Emit & Collect), Flows are mostly cold(produces only if there is a consumer to consume),
in cold you will get the data from the start.
*/

fun main(): Unit = runBlocking{

    val job1 = launch {
        numbersFlow().collect { value ->
            println("Received $value")
        }
    }

    val job2 = launch {
        getAllUsersList().forEach {
            println("Username is $it")
        }
    }
}

// Typical Approach( It will wait for whole list to complete)
suspend fun getAllUsersList(): List<String>{
    val userList = mutableListOf<String>()
    userList.add(getUser(1))
    userList.add(getUser(2))
    userList.add(getUser(3))
    userList.add(getUser(4))
    userList.add(getUser(5))
    return userList
}

suspend fun getUser(userId: Int): String{
    delay(1000)
    return "$userId"
}

fun numbersFlow(): Flow<Int> = flow {
    for (i in 1..8) {
        delay(1000)
        emit(i)
    }
}