package com.nyefan.KIA.chapter2.section4

import java.util.TreeMap
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    demoForLoops()
    demoMapIteration()
    demoIndexIteration()
    demoInRange()
    demoWhenIn()
}

fun fizzbuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "Fizz "
    i % 5 == 0 -> "Buzz "
    else -> "$i "
}

fun demoForLoops() {
    for (i in 1..100) {
        print(fizzbuzz(i))
    }
    println()
    for (i in 100 downTo 1 step 2) {
        print(fizzbuzz(i))
    }
    println()
}

fun demoMapIteration() {
    var binaryRepresentations: Map<Char, String> = HashMap<Char, String>()
    for (character: Char in 'A'..'F') {
        (binaryRepresentations as HashMap)[character] = Integer.toBinaryString(character.toInt())
    }
    binaryRepresentations = TreeMap(binaryRepresentations)
    for ((character: Char, binary: String) in binaryRepresentations) {
        println("$character = $binary")
    }
    binaryRepresentations.forEach { (character, binary) -> println("$binary = $character") }
}

fun demoIndexIteration() {
    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c: Char) = c !in '0'..'9'
fun isNumber(c: Char) = c in '0'..'9'

fun demoInRange() {
    println("isLetter('q'): ${isLetter('q')}")
    println("isNotDigit('x'): ${isNotDigit('x')}")
    println("isNumber('4'): ${isNumber('4')}")
}

fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "Digit"
    in 'a'..'z', in 'A'..'Z' -> "Letter"
    else -> "Unknown"
}

fun demoWhenIn() {
    println(recognize('8'))
    println(recognize('a'))
    println(recognize(')'))
}