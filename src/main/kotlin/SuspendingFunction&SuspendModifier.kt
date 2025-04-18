
/*
A suspend function is a function that can be paused and resumed at a later time without blocking the main thread. It is marked using the suspend modifier.

🧠 What does the suspend modifier do?
The suspend modifier tells the compiler that this function can call other suspend functions, and can be suspended mid-execution.
It doesn't mean the function runs on a background thread automatically.
It must be called from within a coroutine or another suspend function.


✅ delay() is a suspend function — it doesn't block the thread but suspends it for a specified time.

🔄 What is yield() in Kotlin Coroutines?
yield() is a suspending function in Kotlin that:
   Pauses the current coroutine (without blocking the thread),
   Gives a chance for other coroutines to run,
   Then resumes the current coroutine later on the same or another thread (depending on the dispatcher).

Term	                                    Meaning
suspend modifier	       -                Marks a function as suspendable (can be paused & resumed)
suspend function	       -                Can call other suspend functions and run inside coroutines
Coroutine	               -                A lightweight thread for asynchronous code
delay()                    -                A suspend function that simulates a delay without blocking the thread
*/

fun main(){

}



/***

  // Normal
  fun createGuestUser(user: User){
    // Network Call - IO Operation
    val response = createAPIUser(user)
    when(response.status){
        "Success" -> postUserCreation()
        "Error" -> handleUserError()
    }
  }

  // Using suspend modifier
  suspend fun createGuestUser(user: User){
    // Network Call - IO Operation
    val response = createAPIUser(user)
    when(response.status){
        "Success" -> postUserCreation()
        "Error" -> handleUserError()
    }
  }

 */

