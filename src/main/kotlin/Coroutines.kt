
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
 * */


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
 * Dispatchers
 *
 * Dispatcher	                         Runs on
 * Dispatchers.Main	          -          Main thread (UI)
 * Dispatchers.IO	          -          Disk or network I/O
 * Dispatchers.Default	      -          CPU-intensive tasks
 * Dispatchers.Unconfined	  -          Inherits the caller context
 * */