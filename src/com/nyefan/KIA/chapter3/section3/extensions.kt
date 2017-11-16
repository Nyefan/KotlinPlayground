package com.nyefan.KIA.chapter3.section3

import java.util.stream.Collectors

// `this` is the object from which the function is called
fun String.lastChar(): Char = this[this.length - 1]

fun <T : Any> Collection<T>.joinToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""): String {
    return this.stream().map(Any::toString).collect(Collectors.joining(separator, prefix, postfix))
}

open class View {
    open fun internalType(): String = "View"
}

class Button : View() {
    override fun internalType(): String = "Button"
}

fun View.externalType(): String = "View"

fun Button.externalType(): String = "Button"

val String.lastChar: Char
    get() = this[this.length - 1]

