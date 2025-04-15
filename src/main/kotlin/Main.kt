
/***   Basic Concepts :-
 * Program -> set of Instructions -> process -> threads(thread of execution)
 *
 * process :- (processId, state, memory, handle networking, fileSystem etc, threads).
 *
 * each program contain at least one thread.
 *
 * sequential execution :- process(instruction1 ------ instructionN)
 *
 * so can we just re-use the thread when it is waiting for some responses or IO operations?
 *
 * CO -> COOPERATING
 * ROUTINES -> FUNCTIONS
 *
 * */

fun main(args: Array<String>) {
    println("Hello Coroutines!")
}

