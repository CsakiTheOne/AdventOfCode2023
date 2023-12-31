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

    @Test
    fun day03() {
        val testInput = AOC.getTestInputFromFile(3) ?: ""
        day03.testPart1(testInput, 4361)
        val input = AOC.getInput(3) ?: ""
        day03.solvePart1(input, 556367)

        day03.testPart2(testInput, 467835)
        day03.solvePart2(input, 89471771)
    }

    @Test
    fun day04() {
        val testInput = AOC.getTestInputFromFile(4) ?: ""
        day04.testPart1(testInput, 13)
        val input = AOC.getInput(4) ?: ""
        day04.solvePart1(input, 25231)

        day04.testPart2(testInput, 30)
        day04.solvePart2(input, 9721255)
    }

    @Test
    fun day05() {
        val testInput = AOC.getTestInputFromFile(5) ?: ""
        day05.testPart1(testInput, 35L)
        val input = AOC.getInput(5) ?: ""
        day05.solvePart1(input, 510109797L)

//        day05.testPart2(testInput, 46L)
//        day05.solvePart2(input)
    }

    @Test
    fun day06() {
        val testInput = AOC.getTestInputFromFile(6) ?: ""
        day06.testPart1(testInput, 288)
        val input = AOC.getInput(6) ?: ""
        day06.solvePart1(input, 1624896)

        day06.testPart2(testInput, 71503L + 1L)
        day06.solvePart2(input, 32583852L)
    }

    @Test
    fun day07() {
        val testInput = AOC.getTestInputFromFile(7) ?: ""
        day07.testPart1(testInput, 6440)
        val input = AOC.getInput(7) ?: ""
        day07.solvePart1(input, 252656917)

        day07.testPart2(testInput, 5905L)
        val result = day07.solvePart2(input) as Long
        assert(result > 253040090L && result != 253371374L)
    }
}