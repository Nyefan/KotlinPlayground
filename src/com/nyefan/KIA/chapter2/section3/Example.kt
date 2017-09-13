package com.nyefan.KIA.chapter2.section3

import com.nyefan.KIA.chapter2.section3.Color.*

fun main(args: Array<String>) {
    demoEnum()
    demoWhen()
    demoSmartCasts()
}

fun shading(input: String) {
    val input: Double = input.toDouble()
    print(input);
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

interface Expr
data class Num(val value: Int) : Expr
data class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        return (e as Num).value // as Num is not required
    }
    if (e is Sum) {
        return eval(e.right) + eval(e.left)
    }
    throw IllegalArgumentException("Unknown expression")
}

fun evalWhen(e: Expr): Int = when (e) {
    is Num -> e.value
    is Sum -> evalWhen(e.left) + evalWhen(e.right)
    else -> throw IllegalArgumentException("Unknown expression")
}

fun evalLogged(e: Expr): Int {
    return when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value
        }
        is Sum -> {
            val left: Int = evalLogged(e.left)
            val right: Int = evalLogged(e.right)
            println("sum: $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }
}

fun demoSmartCasts() {
    val e: Expr = Sum(Sum(Sum(Num(1), Num(2)), Num(2)), Sum(Num(3), Num(4)))
    println("eval($e): ${eval(e)}")
    println("evalWhen($e): ${evalWhen(e)}")
    println("evalLogged($e): ${evalLogged(e)}")
}

