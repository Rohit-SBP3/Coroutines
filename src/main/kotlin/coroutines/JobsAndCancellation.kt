package coroutines

// Deferred (async) Inherits Job (launch) (def have return object)

/*** Job Hierarchy:- Parent-Child Relationship
 * In Kotlin Coroutines, when you launch a coroutine inside another coroutine's scope,
 * the newly launched coroutine becomes a child of the parent coroutine's Job.
 * This forms a tree-like structure — hence the term Job hierarchy.
 *
 * 🔗 Parent-Child Relationship
 * The parent Job keeps track of all its child Jobs.
 * If a parent coroutine is cancelled, all child coroutines are cancelled automatically.
 * If a child fails (throws exception), by default the parent also gets cancelled (in launch).
 */

import kotlinx.coroutines.*

fun main() = runBlocking {

    /*
    runBlocking (Root Job)
    │
    ├── parentJob (launch)
          ├── childJob1 (launch)
          └── childJob2 (launch)
    */

    val parentJob = launch {
        println("Parent job started")

        val childJob1 = launch {
            delay(1000)
            println("Child job 1 completed")
        }

        val childJob2 = launch {
            delay(2000)
            println("Child job 2 completed")
        }
    }

    delay(1500)
    println("Cancelling parent job...")
    parentJob.join()
    // parentJob.cancelAndJoin()
    println("Main done")


    execute()
}

suspend fun execute(){
    val longJob = CoroutineScope(Dispatchers.IO).launch {
        for(i in 1..1000){
            if(isActive) {
                longRunningTask()
                println("Running ${i}th line")
            }
        }
    }
    delay(4000)
    println("Cancelling the long job")
    longJob.cancel()
    longJob.join()
}

private fun longRunningTask(){
    for(i in 1..10000000){}
}
