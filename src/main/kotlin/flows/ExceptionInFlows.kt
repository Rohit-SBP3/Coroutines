package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{

    launch {
        try {
            emitter().collect{
                println("$it is a Vowel")
                throw Exception("There is a error in consuming data!")
            }
        }catch (e: Exception){
            println("An Exception Occurred in Consumer: ${e.message}")
        }
    }

    println("Hodor!!!")

}

private fun emitter(): Flow<Char> = flow{
    val vowelList = listOf<Char>('A','E','I','O','U')

    /*vowelList.forEach {
        delay(1000)
        emit(it)
        throw Exception("There is an error!")
    }*/

    /*** It does not stop after one exception and continues to emit the data, which is wrong
     * That's why we are using Flow.catch to catch the exceptions*/

    /*vowelList.forEach {
        delay(1000)
        try{
            emit(it)
            throw Exception("There is an error in producing data!")
        }catch (e: Exception){
            println("An Exception Occurred in Producer: {${e.message}}")
        }

    }*/

    vowelList.forEach {
        delay(1000)
        throw Exception("There is an error in producing data!")
        emit(it)
        // throw Exception("There is an error in producing data!")
    }

}.catch {
    println("An Exception Occurred in Producer: {${it.message}}")
    emit('E')
    emit('I')
    emit('O')
    emit('U')
}