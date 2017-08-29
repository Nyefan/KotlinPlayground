package com.nyefan.KIA.chapter1


/**
 * data class - immutable
 * val name: String - parameter "name" of type "String"
 * val age: Int? = null - parameter "age" of nullable type "Int?" with default value "null"
 */
data class Person(val name: String, val age: Int? = null)

/**
 * fun main(args: Array<String>) - entry point to program; functions are first-class values
 * val persons = listOf(...) - from kotlin.collections; included in every Kotlin program
 */
fun main(args: Array<String>) {

    /**
     * Person("Alice"), - default parameters needn't be set explicitly
     * Person("Bob", 29), - but they can be
     * Person("Carl", age = 30) - they can also be named individually
     * Person(name = "Dave", age = 31) - but all variables after the first named one must be named
     * Person(age = 32, name = "Earl") - named variables can be passed in any order
     */
    val persons = listOf(
            Person("Alice"),
            Person("Bob", 29),
            Person("Carl", age = 30),
            Person(name = "Dave", age = 31),
            Person(age = 32, name = "Earl")
    )

    /**
     * val oldest: Person? = ... - value types can be explicitly declared, though they needn't be
     * = persons.maxBy { it.age ?: 0 } - ???? something to do with lambdas; ?: is the elvis operator
     */
    val oldest: Person? = persons.maxBy { it.age ?: 0 }

    /**
     * println("The oldest is: $oldest") - String.format works by default and automatically pulls variables into scope
     */
    println("The oldest is: $oldest")
}