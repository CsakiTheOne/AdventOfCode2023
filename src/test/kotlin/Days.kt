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
        day01pt1.test(testInput, 142)
        val input = AOC.getInput(1) ?: ""
        day01pt1.solve(input)

        val testInput2 = AOC.getTestInputFromFile(1, 2) ?: ""
        day01pt2.test(testInput2, 281)
        day01pt2.solve(input)
    }
}