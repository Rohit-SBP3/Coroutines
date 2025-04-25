package flows

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{

    val channel = Channel<Int>()
    producer(channel)
    consumer(channel)
    Thread.sleep(4000)
}

fun producer(channel: Channel<Int>){
    CoroutineScope(Dispatchers.IO).launch {
        channel.send(1)
        channel.send(2)
    }
}

fun consumer(channel: Channel<Int>){
    CoroutineScope(Dispatchers.IO).launch {
        println(channel.receive())
        println(channel.receive())
    }
}