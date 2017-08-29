package com.nyefan.KIA.chapter2

fun main(args: Array<String>) {
    printMax123()
    demoUnitPassing(fun(message: String) {
        println("demoOverloadedFunctionPassing(fun (message: String) {...}):")
        println("\t$message")
    })
    demoFunctionPassing {
        println("demoFunctionPassing{...}")
        println("\t$it")
    }
    demoOverloadedFunctionPassing { it ->
        println("demoOverloadedFunctionPassing{it -> ...}")
        println("\t$it")
    }
    demoOverloadedFunctionPassing { a, b ->
        println("demoOverloadedFunctionPassing{a, b -> ...}")
        println("\t$a$b")
    }
    demoValVsVar()
}

fun printMax123() {
    val max1 = max1(1, 2)
    val max2 = max2(1, 2)
    val max3 = max3(1, 2)

    println("printMax123():")
    println("\tmax1(1, 2) = $max1")
    println("\tmax2(1, 2) = $max2")
    println("\tmax3(1, 2) = $max3")
}

fun max1(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max2(a: Int, b: Int): Int = if (a > b) a else b

fun max3(a: Int, b: Int) = if (a > b) a else b

fun demoUnitPassing(callback: (String) -> Unit) {
    val message: String = "Success"
    callback(message)
}

fun demoFunctionPassing(callback: (String) -> Unit) {
    callback("Success")
}

fun demoOverloadedFunctionPassing(callback: (String) -> Unit) {
    val message: String = "Success"
    callback.invoke(message)
}

fun demoOverloadedFunctionPassing(callback: (String, String) -> Unit) {
    callback.invoke("Success", "!")
}

fun demoValVsVar() {
    val valueString: String //This can only be set once
    valueString = "Success"
    try {
//        valueString = "Failure" // this line prevents compilation
    } catch (e: Exception) {
        //do nothing
    }
    var variableString: String = "Failure"//This can be set as many times as desired
    variableString = "Success"
    println("demoValVsVar():")
    println("\tvalueString: $valueString")
    println("\tvariableString: $variableString")
}

fun canPerformOperation() = true
