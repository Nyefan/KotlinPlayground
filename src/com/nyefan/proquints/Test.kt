package com.nyefan.proquints

import kotlin.test.assertEquals

fun main(args: Array<String>) {
    testIP()
    testHex()
}

fun testIP() {
    val map: Map<String, String> = mapOf(
            Pair("0q-lusab-babad", "127.0.0.1"),
            Pair("0q-gutih-tugad", "63.84.220.193"),
            Pair("0q-gutuk-bisog", "63.118.7.35"),
            Pair("0q-mudof-sakat", "140.98.193.141"),
            Pair("0q-haguz-biram", "64.255.6.200"),
            Pair("0q-mabiv-gibot", "128.30.52.45"),
            Pair("0q-natag-lisaf", "147.67.119.2"),
            Pair("0q-tibup-zujah", "212.58.253.68"),
            Pair("0q-tobog-higil", "216.35.68.215"),
            Pair("0q-todah-vobij", "216.68.232.21"),
            Pair("0q-sinid-makam", "198.81.129.136"),
            Pair("0q-budov-kuras", "12.110.110.204"),
            Pair("0q-budov-budov", "12.110.12.110"),
            Pair("0q-kuras-kuras", "110.204.110.204"))

    map.forEach { proquint: String, ipString: String ->
        val generated: Proquint = Proquint.fromIP(ipString)
        assertEquals(proquint, generated.toString(), "Generated proquint: $generated from ip string: $ipString does not match expected output: $proquint")
        println("Generated proquint: ${Proquint.fromIP(ipString)}. Expected proquint: $proquint from ip string: $ipString")
    }
}

fun testHex() {
    val map: Map<String, String> = mapOf(
            Pair("0q-lusab-babad", "7F000001"),
            Pair("0q-gutih-tugad", "0x3F54DCC1"),
            Pair("0q-gutuk-bisog", "3F760723"),
            Pair("0q-mudof-sakat", "0x8C62C18D"),
            Pair("0q-haguz-biram", "40FF06C8"),
            Pair("0q-mabiv-gibot", "0x801E342D"),
            Pair("0q-natag-lisaf", "93437702"),
            Pair("0q-tibup-zujah", "0xD43AFD44"),
            Pair("0q-tobog-higil", "D82344D7"),
            Pair("0q-todah-vobij", "0xD844E815"),
            Pair("0q-sinid-makam", "C6518188"),
            Pair("0q-budov-kuras", "0x0C6E6ECC"),
            Pair("0q-budov-budov", "0C6E0C6E"),
            Pair("0q-kuras-kuras", "0x6ECC6ECC")
    )

    map.forEach { proquint: String, hexString: String ->
        val generated: Proquint = Proquint.fromHexString(hexString)
        assertEquals(proquint, generated.toString(), "Generated proquint: $generated from ip string: $hexString does not match expected output: $proquint")
        println("Generated proquint: ${Proquint.fromHexString(hexString)}.  Expected proquint: $proquint from hex string: $hexString")
    }
}