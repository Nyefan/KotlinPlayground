package com.nyefan.KIA.chapter3.section3

/**
 * Adding methods to other people's classes: extension functions and properties
 */

fun main(args: Array<String>) {
    demoExtensionFunctions()
    demoExtensionFunctionOverriding()
    demoExtensionProperties()
}

fun demoExtensionFunctions() {
    println("Kotlin".lastChar())
    println(listOf(1, 2, 3).joinToString(", ", "[", "]"))
}

fun demoExtensionFunctionOverriding() {
    val trueView: View = View()
    val buttonView: View = Button()
    val trueButton: Button = Button()
    println("trueView.internalType() = ${trueView.internalType()}")
    println("trueView.externalType() = ${trueView.externalType()}")
    println("buttonView.internalType() = ${buttonView.internalType()}")
    println("buttonView.externalType() = ${buttonView.externalType()}")
    println("trueButton.internalType() = ${trueButton.internalType()}")
    println("trueButton.externalType() = ${trueButton.externalType()}")
}

fun demoExtensionProperties() {
    println("Kotlin".lastChar)
}
