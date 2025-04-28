package sharedflow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow


/***
Feature                       | Flow                                                       | SharedFlow

Cold or Hot?                  | Cold → starts when collected                               | Hot → always active, emits even without collectors
Start emitting                | Only when a collector starts listening                     | Always emits (even if no one is listening)
Multiple collectors           | Each collector gets a fresh stream                         | All collectors get the same emissions
Use case                      | Simple data streams (like from database, API)              | Broadcast events (like UI events, Notifications)
Backed by                     | Coroutine builder (flow {})                                | Based on an internal replay buffer
Replay old values?            | No                                                         | Can replay last N emitted values
 */

fun main() = runBlocking{


    launch {
        val m = manu()
        m.collect {
            println("This is $it")
        }
    }

    launch {
        val mc = manu()
        delay(2500)
        mc.collect {
            println("This is not $it")
        }
    }

    println("Groot!")
}

@OptIn(DelicateCoroutinesApi::class)
fun manu() : Flow<Int> {

    val mutableSharedFlow = MutableSharedFlow<Int>(2)
    val list = listOf(1,2,3,4,5,6,7,8,9)

    GlobalScope.launch {
        list.forEach {
            delay(1000)
            mutableSharedFlow.emit(it)
        }
    }

    return mutableSharedFlow
}
