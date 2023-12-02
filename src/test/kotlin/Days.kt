import kotlin.test.Test
import days.*
import utils.AOC

class Days {
    @Test
    fun getInputFromWebTest() {
        val lastYearInput = AOC.getInputFromWeb(1, 2022)
        println(lastYearInput)
        assert(!lastYearInput.isNullOrBlank())
    }

    @Test
    fun day01() {
        val testInput = AOC.getTestInputFromFile(1) ?: ""
        day01.testPart1(testInput, 142)
        val input = AOC.getInput(1) ?: ""
        day01.solvePart1(input, 54644)

        val testInput2 = AOC.getTestInputFromFile(1, 2) ?: ""
        day01.testPart2(testInput2, 281)
        day01.solvePart2(input, 53348)
    }

    @Test
    fun day02() {
        val testInput = AOC.getTestInputFromFile(2) ?: ""
        day02.testPart1(testInput, 8)
        val input = AOC.getInput(2) ?: ""
        day02.solvePart1(input, 2061)

        day02.testPart2(testInput, 2286)
        day02.solvePart2(input, 72596)
    }
}