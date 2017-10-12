package com.nyefan.proquints

import java.util.*
import kotlin.experimental.and
import kotlin.experimental.or

class Proquint(arr: ShortArray) {
    companion object {
        const val prefix: String = "0q-"
        const val separatorChar: Char = '-'
        const val consonantBits: Int = 15
        const val vowelBits: Int = 3
        const val consonants: String = "bdfghjklmnprstvz"
        const val vowels: String = "aiou"
        private val hexDecoderMap: Map<Char, Int> = mapOf(
                Pair('0', 0),
                Pair('1', 1),
                Pair('2', 2),
                Pair('3', 3),
                Pair('4', 4),
                Pair('5', 5),
                Pair('6', 6),
                Pair('7', 7),
                Pair('8', 8),
                Pair('9', 9),
                Pair('A', 10), Pair('a', 10),
                Pair('B', 11), Pair('b', 11),
                Pair('C', 12), Pair('c', 12),
                Pair('D', 13), Pair('d', 13),
                Pair('E', 14), Pair('e', 14),
                Pair('F', 15), Pair('f', 15)
        )
        //Could be optimized by declaring it directly as well
        private val hexEncoderMap: Map<Int, Char> = mapOf(*hexDecoderMap.map { (key, value) -> Pair(value, key.toUpperCase()) }.toTypedArray())

        fun fromString(proquintString: String): Proquint {
            val trimmed: List<String> = proquintString
                    .removePrefix("0q-")
                    .split('-')
            val foldIndexed = trimmed
                    .foldIndexed(
                            ShortArray(trimmed.size),
                            { index: Int, acc: ShortArray, quint: String ->
                                //TODO: create a separate encode and decode map for quints
                                val number1 = consonants.indexOf(quint[0], ignoreCase = true)
                                val number2 = vowels.indexOf(quint[1], ignoreCase = true)
                                val number3 = consonants.indexOf(quint[2], ignoreCase = true)
                                val number4 = vowels.indexOf(quint[3], ignoreCase = true)
                                val number5 = consonants.indexOf(quint[4], ignoreCase = true)
                                acc[index] = 0
                                        .or(number1.shl(12))
                                        .or(number2.shl(10))
                                        .or(number3.shl(6))
                                        .or(number4.shl(4))
                                        .or(number5)
                                        .toShort()
                                acc
                            }
                    )
            return Proquint(foldIndexed)
        }

        @JvmStatic
        fun fromIP(ipString: String): Proquint {
            return fromDottedQuad(ipString)
        }

        @JvmStatic
        fun fromDottedQuad(dottedQuad: String): Proquint {
            return Proquint(dottedQuad.split('.').map { it.toInt().and(255).toByte() }.toByteArray())
        }

        @JvmStatic
        fun fromHexString(hexString: String): Proquint {
            val trimmed = hexString
                    .removePrefix("0x")
                    .filter { char: Char -> char != ' ' }
            val foldIndexed = trimmed
                    .toCharArray()
                    .foldIndexed(
                            ShortArray(Math.ceil(trimmed.length / 4.0).toInt()),
                            { index: Int, acc: ShortArray, char: Char ->
                                val byte = hexDecoderMap[char] ?: throw NumberFormatException("Hex String contained non-hex character: $char")
                                when (index % 4) {
                                    0 -> acc[index / 4] = byte.shl(12).toShort()
                                    1 -> acc[index / 4] = acc[index / 4].or(byte.shl(8).toShort())
                                    2 -> acc[index / 4] = acc[index / 4].or(byte.shl(4).toShort())
                                    3 -> acc[index / 4] = acc[index / 4].or(byte.shl(0).toShort())
                                }
                                acc
                            }
                    )
            return Proquint(foldIndexed)
        }

        @JvmStatic
        fun fromBase64String(base64String: String): Proquint {
            return Proquint(Base64.getDecoder().decode(base64String))
        }
    }

    private val value: ShortArray = arr.clone()
    private val byteValue: ByteArray = value.foldIndexed(
            ByteArray(value.size * 2),
            { index: Int, acc: ByteArray, short: Short ->
                acc[index * 2] = short.toInt().and(255).toByte()
                acc[index * 2 + 1] = short.toInt().shr(8).and(255).toByte()
                acc
            }
    )

    constructor(arr: ByteArray) : this(
            arr.foldIndexed(
                    ShortArray(Math.ceil(arr.size / 2.0).toInt()),
                    { index: Int, acc: ShortArray, byte: Byte ->
                        if (index % 2 == 0) {
                            acc[index / 2] = byte.toInt().shl(8).toShort()
                        } else {
                            acc[index / 2] = acc[index / 2].toInt().or(byte.toInt().and(255)).toShort()
                        }
                        acc
                    }))

//    fun toDottedQuad(): String {
//        return value.joinToString(separator = ".") {
//
//        }
//    }

    fun toHexString(separateDuplets: Boolean = false): String {
        return value.joinToString(separator = "", prefix = "0x") {
            val char1: Char = hexEncoderMap.getValue(it.toInt().and(15.shl(12)).shr(12))
            val char2: Char = hexEncoderMap.getValue(it.toInt().and(15.shl(8)).shr(8))
            val char3: Char = hexEncoderMap.getValue(it.toInt().and(15.shl(4)).shr(4))
            val char4: Char = hexEncoderMap.getValue(it.toInt().and(15))
            if (separateDuplets) {
                String(charArrayOf(char1, char2, ' ', char3, char4, ' '))
            }
            String(charArrayOf(char1, char2, char3, char4))
        }
    }

    fun toBase64String(): String {
        return Base64.getEncoder()
                .encodeToString(
                        value.foldIndexed(
                                ByteArray(value.size * 2),
                                { index: Int, acc: ByteArray, short: Short ->
                                    acc[2 * index] = short.toInt().and(255.shl(8)).shr(8).toByte()
                                    acc[2 * index + 1] = short.toInt().and(255).toByte()
                                    acc
                                }
                        ))
    }

    override fun toString(): String {
        return value.joinToString(separator = separatorChar.toString(), prefix = prefix) {
            val char1: Char = consonants[consonantBits.shl(12).and(it.toInt()).shr(12)]
            val char2: Char = vowels[vowelBits.shl(10).and(it.toInt()).shr(10)]
            val char3: Char = consonants[consonantBits.shl(6).and(it.toInt()).shr(6)]
            val char4: Char = vowels[vowelBits.shl(4).and(it.toInt()).shr(4)]
            val char5: Char = consonants[consonantBits.and(it.toInt())]
            String(charArrayOf(char1, char2, char3, char4, char5))
        }
    }


}

class Generator {
    fun generateRandom(bits: Int): Proquint {
        val numCells: Int = Math.ceil(bits / 16.0).toInt()

        val randomBytes = ByteArray(numCells * 2)
        Random().nextBytes(randomBytes)

        println(randomBytes.asList())

        val remainder = bits.rem(16)

        if (bits % 16 != 0) {
            val init: Int = (-1).shl(16).shr(remainder)
            val penultimateByteMask: Byte = init.and(255.shl(8)).shr(8).toByte()
            val lastByteMask: Byte = init.and(255).toByte()

            randomBytes[randomBytes.lastIndex - 1] = randomBytes[randomBytes.lastIndex - 1].and(penultimateByteMask)
            randomBytes[randomBytes.lastIndex] = randomBytes[randomBytes.lastIndex].and(lastByteMask)
        }
        println(randomBytes.asList())



        return Proquint(randomBytes)
    }
}