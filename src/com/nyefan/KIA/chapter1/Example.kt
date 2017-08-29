package com.nyefan.KIA.chapter1


/**
 * data class - immutable
 * val name: String - parameter "name" of type "String"
 * val age: Int? = null - parameter "age" of nullable type "Int?" with default value "null"
 */
data class Person(val name: String, val age: Int? = null)

fun main(args: Array<String>) {

    val persons = listOf(
            Person("Alice"),
            Person("Bob", 29),
            Person("Carl", age = 30),
            Person(name = "Dave", age = 31)
    )

    val oldest: Person? = persons.maxBy { it.age ?: 0 }

    println("The oldest is: $oldest")
}