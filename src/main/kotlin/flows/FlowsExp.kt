package flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.flow

fun main() = runBlocking{

    val job1 = launch {
        producer().collect {
            println("Consumer 1 gets ${it.toString()}")
        }
    }

    // Even the producer has start producing every consumer will get it from the start...
    // In terms of cold streams each of the consumer is independent and gets data from the starting of the production...
    // For ex:- our job2 started after 2500ms but get the data from 1.
    // In hot the previous produced data is not available to the consumer...
    val job2 = launch {
        producer().collect {
            delay(2500)
            println("Consumer 2 gets ${it.toString()}")
        }
    }

    // for cancelling the flow
    CoroutineScope(Dispatchers.IO).launch {
        delay(5500)
        job1.cancel()
    }

    val ul: List<User> = listOf(
        User(1,"Rohit Singh","Avas Vikas"),
        User(2,"Previka Singh", "Jadoo Pipra")
    )

    launch {
        val sp = getShippingPrice(ul)
        getShippingPrice(ul).collect {
            println("Shipping Charge is $it")
        }
    }


    println("Main")

}

data class User(
    val id: Int,
    val name: String,
    val address: String
)

fun getShippingPrice(userList: List<User>): Flow<Int> = flow{
    userList.forEach {
        delay(1000)
        emit(it.name.length + it.id + it.address.length)
    }
}

// You can have multiple consumer for the same producer.............
fun producer(): Flow<Int> = flow{
    val l = listOf(1,2,3,4,5,6,7,8,9)
    l.forEach {
        delay(1000)
        emit(it)
    }
}