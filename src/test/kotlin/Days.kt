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
    fun day00() {
        val testInput = AOC.getTestInputFromFile(3) ?: ""
        day00pt1.test(testInput, 198)
        val input = AOC.getInputFromWeb(3, 2021) ?: ""
        day00pt1.solve(input)
    }

    @Test
    fun day01() {

    }
}