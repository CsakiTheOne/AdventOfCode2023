package days

import utils.Puzzle

val day00pt1 = Puzzle {
    val lines = it.lines().filter { line -> line.isNotBlank() }
    val lineLength = lines[0].length

    var gamma = ""
    var epsilon = ""
    repeat(lineLength) { i ->
        val currentBits = lines.map { line -> line[i] }.count { bit -> bit == '1' }
        gamma += if (currentBits > lines.size / 2) '1' else '0'
        epsilon += if (currentBits > lines.size / 2) '0' else '1'
    }

    val gammaInt = gamma.toInt(2)
    val epsilonInt = epsilon.toInt(2)

    return@Puzzle gammaInt * epsilonInt
}
