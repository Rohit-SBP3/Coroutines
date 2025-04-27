package flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.math.sqrt
import kotlin.system.measureTimeMillis

/*** Terminal Operators:
 * A Terminal Operator is an operation that starts the flow and collects the emitted data.
 * Until you use a terminal operator, the flow does nothing (because Flow is cold by default).
 * It "consumes" the flow.
 * they are suspend functions...forex(collect, emit)
 *
 * Terminal Operator            What it does                                                   Example
 * collect,                     Collect all emitted values (most common)                       flow.collect { println(it) }
 * toList                       Collect all values into a List                                 val list = flow.toList()
 * toSet                        Collect all values into a Set                                  val set = flow.toSet()
 * first                        Get the first emitted value and cancel                         val first = flow.first()
 * firstOrNull                  Get the first value or return null if none                     val first = flow.firstOrNull()
 * last                         Get the last emitted value                                     val last = flow.last()
 * single                       Expect exactly one value (error if more or none)               val only = flow.single()
 * singleOrNull                 Same but returns null if none                                  val only = flow.singleOrNull()
 * reduce                       Accumulate values into a single result (with an operation)     flow.reduce { acc, value -> acc + value }
 * fold                         Like reduce, but you can specify an initial value              flow.fold(0) { acc, value -> acc + value }
 * */

/***
 * ⚡ Flow Types
 *
 * Type	                 Meaning	                                                            Example
 * Cold Flow	         Doesn't emit until collected	                                        flow { emit(1) }
 * Hot Flow	             Emits values whether collected or not	                                SharedFlow, StateFlow
 * StateFlow	         Holds a single latest value (like LiveData)	                        MutableStateFlow(initialValue)
 * SharedFlow	         Broadcasts to multiple collectors (similar to PublishSubject in Rx)	MutableSharedFlow()
 * ChannelFlow	         Flow builder using channels	                                        channelFlow { send(data) }
 *
 *
 * 🌟 Flow Operators
 * Operators are used to transform, combine, filter, collect, etc.
 *
 *
 * Operator	                         Purpose	                                          Example
 * map	                             Transform values	                                  .map { it * 2 }
 * filter	                         Only pass values matching a condition	              .filter { it % 2 == 0 }
 * take,	                         Take only first N values	                          .take(5)
 * drop,	                         Drop first N values	                              .drop(3)
 * collect	                         Terminal operator to consume flow	                  .collect { println(it) }
 * toList, toSet	                 Collect values into list/set                      	  .toList()
 * flatMapConcat, flatMapMerge	     Flatten nested flows	                              Useful for mapping + merging
 * zip                             	 Combine two flows item by item                       flow1.zip(flow2) { a, b -> ... }
 * catch	                         Handle errors inside a flow	                     .catch { emit(defaultValue) }
 * onEach	                         Do side actions for every emission	                 .onEach { println(it) }
 *
 * */

fun main() = runBlocking{

    launch {
        val time = measureTimeMillis {
            producePrimes(50)
                .onCompletion { println("Range reached") }
                .onStart {
                    //emit(-1)
                    println("Producing prime numbers takes some time")
                }
                .buffer(3)
                .onEach { print("This is your prime number: ") }
                .collect {
                    // consumer is slow so we buffer
                    delay(1500)
                    println(it)
                }
        }
        println(time)
    }

    /*launch {
        val two = producePrimes(5).first()
        val nPList = producePrimes(10).map {it+1}.filter { it % 2 == 0 }.toList()
        println(two)
        println(nPList)
    }*/

    /*launch {
             getNotes()
            .map{ FormattedNotes(it.isActive, it.title.uppercase(), it.description ) }
            .filter { it.isActive }
            .collect{
                println(it.toString())
            }
    }*/

    println("Hello I am under the water!")
}

private fun getNotes(): Flow<Notes>{

    val notesList = listOf<Notes>(
        Notes(1,true,"Apple","apple"),
        Notes(2,false,"Banana","banana"),
        Notes(3,true,"Cat","cat"),
        Notes(4,true,"Dog","dog"),
        Notes(5,false,"Elephant","elephant")
    )

    return notesList.asFlow()
}

data class Notes(val id: Int, val isActive: Boolean, val title: String, val description: String)
data class FormattedNotes(val isActive: Boolean, val title: String, val description: String)

fun producePrimes(range: Int) = flow<Int>{
    val list = sieveOfEratosthenes(range)
    list.forEach {
        delay(1000)
        if(it != -1) emit(it)
    }
}

private fun sieveOfEratosthenes(range: Int): List<Int> {

    if(range < 2) return emptyList()

    val isPrime = BooleanArray(range+1){true}
    isPrime[0] = false
    isPrime[1] = false

    for(i in 2..sqrt(range.toDouble()).toInt()) {
        if (isPrime[i]) {
            for (j in i * i..range step i) {
                isPrime[j] = false
            }
        }
    }

    return isPrime.mapIndexed { index, prime -> if (prime) index else -1}
}