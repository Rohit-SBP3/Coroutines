package flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{

    launch {
        producer().collect {
            println(it.toString())
        }
    }

    val ul: List<User> = listOf(
        User(1,"Rohit Singh","Avas Vikas"),
        User(2,"Previka Singh", "Jadoo Pipra")
    )

    launch {
        val sp = getShippingPrice(ul)
        getShippingPrice(ul).collect {
            println("Shipping Charge is $it")
        }
    }

    println("Main")

}

data class User(
    val id: Int,
    val name: String,
    val address: String
)

fun getShippingPrice(userList: List<User>): Flow<Int> = flow{
    userList.forEach {
        delay(1000)
        emit(it.name.length + it.id + it.address.length)
    }
}

fun producer(): Flow<Int> = flow{
    val l = listOf<Int>(1,2,3,4,5,6,7,8,9)
    l.forEach {
        delay(1000)
        emit(it)
    }
}