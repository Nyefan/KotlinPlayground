package com.nyefan.KIA.chapter2.section2

import java.util.*

fun main(args: Array<String>) {
    demoMutableVsImmutableClassValues()
    demoCustomAccessors()
    demoJavaImport()
}

data class Person(val name: String, var isMarried: Boolean)

fun demoMutableVsImmutableClassValues() {
    val person: Person = Person("Iramid", false)
    println(person)
    person.isMarried = true
    println(person)
//    person.name = "Kyle" // This line prevents compilation
}

data class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }
}

fun demoCustomAccessors() {
    val rectangle: Rectangle = Rectangle(10, 20)
    val square: Rectangle = Rectangle(15, 15)
    println(rectangle)
    println(square)
}

fun demoJavaImport() {
    val random: Random = Random()
    println(Rectangle(random.nextInt(), random.nextInt()))
}