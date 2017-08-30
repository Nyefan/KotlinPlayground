package com.nyefan.KIA.chapter2.section3

import com.nyefan.KIA.chapter2.section3.Color.*

fun main(args: Array<String>) {
    demoEnum()
    demoWhen()
}

enum class Color(
        val r: Int = 0, val g: Int = 0, val b: Int = 0
) {
    //Enum Values
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);

    //Class Functions
    fun rgb() = (r * 256 + g) * 256 + b
}

fun demoEnum() {
    println(Color.BLUE.rgb())
    println(Color.VIOLET)
    println(Color.VIOLET.rgb())
}

fun getMnemonic(color: Color) = when (color) {
    Color.RED -> "Richard"
    Color.ORANGE -> "Of"
    Color.YELLOW -> "York"
    Color.GREEN -> "Gave"
    Color.BLUE -> "Battle"
    Color.INDIGO -> "In"
    Color.VIOLET -> "Vain"
}

fun getWarmth(color: Color) = when (color) {
    Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
    Color.GREEN -> "neutral"
    Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
}

fun mix(c1: Color, c2: Color) = when (setOf(c1, c2)) {
    setOf(RED, YELLOW) -> ORANGE
    setOf(YELLOW, BLUE) -> GREEN
    setOf(BLUE, VIOLET) -> INDIGO
    setOf(RED, BLUE) -> VIOLET
    else -> throw Exception("BROWN")
}

fun mixOptimized(c1: Color, c2: Color) = when {
    (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
    else -> throw Exception("TOO MUCH WORK")
}

fun demoWhen() {
    println(getMnemonic(Color.BLUE))
    println(getWarmth(Color.GREEN))
    println(mix(RED, BLUE))
    println(mixOptimized(RED, YELLOW))
}