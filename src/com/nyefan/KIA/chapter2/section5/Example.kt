package com.nyefan.KIA.chapter2.section5

import java.io.BufferedReader
import java.io.StringReader

fun main(args: Array<String>) {
    demoThrow()
    demoTry()
}

fun percent(number: Int): Int {
    val percentage =
            if (number in 0..100) {
                number
            } else {
                throw IllegalArgumentException("$number is not between 0 and 100")
            }
    return percentage
}


fun demoThrow() {
    catchPrint { println(percent(120)) }
    catchPrint { println(percent(30)) }
}

fun catchPrint(func: () -> Unit) = try {
    func()
} catch (e: Exception) {
    println(e)
}

fun readNumber(bufferedReader: BufferedReader): Int? {
    return try {
        val line = bufferedReader.readLine()
        Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        null
    } finally {
        bufferedReader.close()
    }
}

fun demoTry() {
    println(readNumber(BufferedReader(StringReader("123"))))
    println(readNumber(BufferedReader(StringReader("not a number"))))
}

