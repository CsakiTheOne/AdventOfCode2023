package days

import utils.Puzzle

val day01pt1 = Puzzle(
    day = 1,
    part = 1,
) { input ->
    val lines = input.lines()
    val calibrationValues = lines.map { line ->
        val digits = line.filter { it.isDigit() }
        if (digits.isNotBlank()) {
            return@map "${digits.first()}${digits.last()}".toIntOrNull()
        }
        return@map null
    }.filterNotNull()
    return@Puzzle calibrationValues.sum()
}

val day01pt2 = Puzzle(
    day = 1,
    part = 2,
) { input ->

    fun getNumbersFromLine(line: String): List<Int> {
        val stringDigitMap = mapOf(
            "zero" to '0',
            "one" to '1',
            "two" to '2',
            "three" to '3',
            "four" to '4',
            "five" to '5',
            "six" to '6',
            "seven" to '7',
            "eight" to '8',
            "nine" to '9',
        )

        val newLine = mutableListOf<Int>()

        line.forEachIndexed { index, c ->
            if (c.isDigit()) {
                newLine.add(c.toString().toInt())
            }
            else {
                val substring = line.substring(index)
                stringDigitMap.forEach { (letters, value) ->
                    if (substring.startsWith(letters)) {
                        newLine.add(value.toString().toInt())
                    }
                }
            }
        }

        return newLine
    }

    val lines = input.lines()

    val numbers = lines.mapNotNull { line ->
        return@mapNotNull getNumbersFromLine(line).ifEmpty { null }
    }

    val calibrationValues = numbers.mapNotNull { line ->
        "${line.first()}${line.last()}".toIntOrNull()
    }

    return@Puzzle calibrationValues.sum()
}