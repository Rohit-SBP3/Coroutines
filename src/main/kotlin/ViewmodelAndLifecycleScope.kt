
/***

🔷 1. viewModelScope
What it is: A coroutine scope tied to the ViewModel.
Auto-cancelled when the ViewModel is cleared (usually when the screen is destroyed).
Use it for: Anything related to UI logic/data fetching that should live as long as the ViewModel.

✅ Example:
class MyViewModel : ViewModel() {
    fun fetchData() {
        viewModelScope.launch {
           val data = repository.getData()
           // update LiveData or StateFlow
        }
    }
}

✅ Automatically cancels when ViewModel is cleared.
✅ No memory leaks.
✅ Ideal for making network calls, DB fetch, etc.

🔷 2. lifecycleScope
What it is: A coroutine scope tied to a LifecycleOwner (like an Activity or Fragment).
Auto-cancelled based on the component's lifecycle state.
Use it for: UI tasks that only need to live while the UI is visible or active.

✅ Example in Fragment:
class MyFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          lifecycleScope.launch {
              val data = fetchSomeData()
              // update UI
          }
    }
}

🔄 Lifecycle-aware variants:
lifecycleScope.launchWhenCreated { ... }
lifecycleScope.launchWhenStarted { ... }
lifecycleScope.launchWhenResumed { ... }


🔁 Comparison Table:
Scope	           Bound To       	      Auto-Cancels On	          Common Use Case
viewModelScope	   ViewModel,	          ViewModel cleared	          Data loading, business logic
lifecycleScope	   Activity/Fragment	  Lifecycle destroyed	      UI updates, animations, navigation

 * */


