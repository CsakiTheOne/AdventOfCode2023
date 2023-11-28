import days.day00pt1
import utils.AOC
import utils.Puzzle

val day = 0
val puzzle = Puzzle {
    return@Puzzle it
}

fun main() {
    puzzle.test("", "")
    AOC.getInput(day)?.let { puzzle.solve(it) }
}