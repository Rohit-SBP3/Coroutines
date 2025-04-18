
/*** ----------------------------------------- Coroutines ----------------------------------------------
 * Coroutines are lightweight threads that allow you to write asynchronous code as if it were sequential,
 * making it easier to read and maintain.
 *
 * Why Use Coroutines?
 * ✅ Non-blocking by default
 * ✅ Lightweight (thousands of coroutines can run on a few threads)
 * ✅ Structured concurrency
 * ✅ Easy to cancel and manage
 *
 * Coroutines are just like threads (lite-weight thread) but is not thread,
 * Coroutines runs on the top of the threads.
 *
 * Coroutines :-
 * 1. Coroutine Scope - defines lifetime
 * 2. Coroutine Context - tells about the threads
 *
 * */

// Major difference between threads and coroutines as both creates a new thread and run the task on it
// Coroutines helps to implement functionality that can be suspended & later resumed at specific
// points without blocking the threads.

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() {


    /*
    🧠 launch starts a coroutine
    🧠 delay is a suspending function — it doesn't block the thread
    🧠 runBlocking is used to bridge normal blocking code and coroutines (typically only
    */

}

/***
 * Coroutine Builders
 * Builder	             Description
 * launch	      -      Starts a new coroutine, doesn't return result
 * async	      -      Returns a Deferred<T> for a result (use await())
 * runBlocking	  -      Blocks current thread to wait for coroutine (not for Android UI!)
 * */

/***
 * Coroutine Scopes
 * Scopes define the lifecycle of coroutines.
 *
 * GlobalScope: lives throughout the app lifetime (avoid in most cases)
 *
 * CoroutineScope: usually tied to lifecycle (e.g., viewModelScope, lifecycleScope)
 *
 * MainScope(): useful in Android for UI-related tasks
 *
 * */

/***
 * Dispatchers :- dispatchers is a way to define threads on which Coroutines are executed.
 * Dispatcher	                         Runs on
 * Dispatchers.Main	          -          Main thread (UI)
 * Dispatchers.IO	          -          Disk or network I/O
 * Dispatchers.Default	      -          CPU-intensive tasks
 * Dispatchers.Unconfined	  -          Inherits the caller context
 * */