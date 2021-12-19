package com.santimattius.template.objectmothers

object UnitTestUtils {

    private const val STRING_LENGTH = 10
    private val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')

    fun randomString(length: Int = STRING_LENGTH): String {
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    fun randomInt() = (100..200).random()
}
