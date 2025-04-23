package coroutines

import kotlinx.coroutines.*

fun main() =  runBlocking  {

    /*CoroutineScope(Dispatchers.IO).launch {
        launch {  executeTask() }
        launch { executeTask1() }
    }
    Thread.sleep(3500)*/

    launch { executeTask() }
    launch { executeTask1() }
    println("All done!!!!")

}


suspend fun executeTask(){
    println("Before withContext launch")
    withContext(Dispatchers.IO) {
        delay(3000)
        println("Inside")
    }
    println("After withContext launch")
}

suspend fun executeTask1(){
    println("Before Global scope launch")
    GlobalScope.launch {
        delay(2000)
        println("Inside")
    }.join()
    println("After Global scope launch")
}