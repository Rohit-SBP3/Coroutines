package sharedflow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect


/*** SharedFlow vs StateFlow
Feature                        | StateFlow                                   | SharedFlow
Purpose                        | Holds a single up-to-date value             | Broadcasts events or multiple values
Value                          | Always has a current value                  | Does not necessarily have a value
Initial value                  | Required when created                       | Not needed
Replay                         | Only latest value                           | Can replay multiple past values (replay = n)
Typical use case               | UI state, form data, screen state           | One-time events, multiple emissions (e.g., Snackbar events, navigation)
Backed by                      | MutableStateFlow(initialValue)              | MutableSharedFlow()
Update Value                   | .value = newValue or .emit(newValue)        | .emit(value)
 */

/*** StateFlow vs LiveData
Feature                      | StateFlow (Kotlin Flow API)                 | LiveData (AndroidX Lifecycle API)
Designed by                  | Kotlin team                                 | Android team
Used with                    | Jetpack Compose + XML                       | XML-based UI (traditional Android Views)
Hot/Cold                     | Hot flow (always active)                    | Hot observable (lifecycle-aware)
Thread-safety                | Fully thread-safe                           | Fully thread-safe
Lifecycle awareness          | ❌ Not lifecycle-aware by default           | ✅ Lifecycle-aware (stops when no observers)
Coroutines support           | ✅ First-class coroutine support            | 🚫 Needs viewModelScope.launch {} to bridge
Observability                | collect block                               | observe(owner) {} method
Backpressure support         | ✅ (Flows have backpressure handling)       | 🚫 No backpressure handling
Default value                | Requires an initial value                   | Can be null initially
Interoperability             | Newer (recommended for Compose)             | Older (still used in XML-based projects)
 */



fun main() = runBlocking{

    launch {
        generator().collect{
            println("The latest value is: $it")
        }
    }

    launch {
        delay(6000)
        generator().value
    }

    println("I am groot!")

}

@OptIn(DelicateCoroutinesApi::class)
private fun generator(): StateFlow<Int> {

    val msf = MutableStateFlow(0)
    val list = listOf(1,2,3,4,5)


    GlobalScope.launch{
        list.forEach {
            delay(1000)
            msf.emit(it)
        }
    }

    return msf
}