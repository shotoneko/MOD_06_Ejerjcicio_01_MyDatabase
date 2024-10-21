package modulo_05.sprint.mydatabase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.properties.Delegates

var name: String by Delegates.observable("initial value") {
        prop, old, new ->
    println("Name changed from $old to $new")
}

fun main() {
    name = "John" // Prints "Name changed from initial value to John"
}