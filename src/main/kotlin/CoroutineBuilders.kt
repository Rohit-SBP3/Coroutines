import kotlinx.coroutines.*

/*** Coroutine Builders :-

    Functions that helps in creating coroutines.
    We have already seen launch functions.
    CoroutineScope(Dispatchers.IO).launch {
       // launch
    }

    Builder	                             Description
    launch	                             Fire and forget. Starts a coroutine that doesn't return a result.
    async	                             Starts a coroutine that returns a Deferred (i.e., like Future) — use .await() to get the result.
    runBlocking	                         Used to bridge regular and suspending code, blocks the current thread until coroutine completes.
    withContext	                         Used to switch the coroutine context, returns a result.

 */

/*   JOB Lifecycle
🔄 Breakdown of Each State

State	                  What it Means
New	                      Created but not started (rare — usually happens with lazy coroutines)
Active	                  The coroutine is currently running
Completing	              Coroutine is finishing its work (executing finally blocks, etc.)
Completed	              Coroutine is done successfully
Cancelling	              Coroutine was cancelled, but still cleaning up (finally blocks, etc.)
Cancelled	              Coroutine has been cancelled and is now fully done
*/

fun main() = runBlocking {

    /*
    ✅ What is a Job?
    In Kotlin Coroutines, a Job represents a handle to a coroutine — it's like a remote control. When you launch a coroutine using launch, it returns a Job which lets you:
    Wait for the coroutine to complete (join())
    Cancel the coroutine (cancel())
    Check if it’s active, completed, or cancelled
    */

    val job1: Job = CoroutineScope(Dispatchers.IO).launch {
        var f = 0
        f = getFBFollowers()
        println("You have $f followers on Facebook!")
    }

    /*
    ⚠️ Note on join()
    join() is non-blocking for coroutines but suspends the current coroutine.
    So it's safe and useful when coordinating between multiple coroutines.
    */
    job1.join()

    val job2: Unit = CoroutineScope(Dispatchers.IO).async {
        val f = getFBFollowers()
        println("You have $f followers on Youtube!")
    }.await()

    val inst = CoroutineScope(Dispatchers.IO).async {
        getFBFollowers()
    }
    println("You have ${inst.await()} followers on Instagram!")



}

private suspend fun getFBFollowers(): Int{
    delay(1000)
    return 568
}