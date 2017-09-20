package com.nyefan.KIA.chapter3.section1

fun main(args: Array<String>) {
    demoCollections()
    demoKotlinCollectionFunctions()
}

fun demoCollections() {
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)
}

fun demoKotlinCollectionFunctions() {
    val strings = listOf("first", "second", "fourteenth")
    println(strings.last())

    val numbers = setOf(1, 2, 14)
    println(numbers.max())
}

