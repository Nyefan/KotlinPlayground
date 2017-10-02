@file:JvmName(name = "C1S2E")

package com.nyefan.KIA.chapter3.section2

//private static int opCount = 0;
//public static void setOpCount(int newOpCount) { opCount = newOpCount; }
//public static int getOpCount() { return opCount; }
var opCount: Int = 0
//private static int MAX_INT = Integer.MAX_VALUE
//public static int getMax() { return MAX_INT; }
val MAX_INT: Int = Int.MAX_VALUE
//public static final int UNIX_LINE_SEPARATOR = '\n'
const val UNIX_LINE_SEPARATOR: Char = '\n'

fun main(args: Array<String>) {
    demoNamedArguments()
}

fun <T> joinToString(
        collection: Collection<T>,
        separator: String = " ",
        prefix: String = "{",
        postfix: String = "}"
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun demoNamedArguments() {
    val collection: List<Int> = listOf(1, 2, 3)
    joinToString(collection, "; ", "(", ")")
    joinToString(collection, separator = ", ", prefix = "[", postfix = "]")
    joinToString(collection, separator = ":")
}