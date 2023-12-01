package days

import utils.Puzzle

val day01pt1 = Puzzle { input ->
    val lines = input.lines()
    val numbers = lines.map { line ->
        val digits = line.filter { it.isDigit() }
        if (digits.isNotBlank()) {
            return@map "${digits.first()}${digits.last()}".toIntOrNull()
        }
        return@map null
    }.filterNotNull()
    return@Puzzle numbers.sum()
}

val day01pt2 = Puzzle { input ->
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

    val lines = input.lines()

    val numberLines = lines.map { line ->
        val newLine = mutableListOf<Int>()

        line.forEachIndexed { index, c ->
            if (c.isDigit()) newLine += c.toString().toInt()
            else {
                val subLine = line.substring(index)
                stringDigitMap.forEach { (letters, value) ->
                    if (subLine.startsWith(letters)) {
                        newLine += value.toString().toInt()
                    }
                }
            }
        }

        newLine
    }

    val numbers = numberLines.map { line ->
        if (line.isEmpty()) return@map null
        "${line.first()}${line.last()}".toIntOrNull()
    }.filterNotNull()

    return@Puzzle numbers.sum()
}