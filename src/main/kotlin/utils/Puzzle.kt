package utils

class Puzzle(
    val day: Int,
    val part1: Puzzle.(input: String) -> Any?,
    val part2: Puzzle.(input: String) -> Any?
) {
    fun testPart1(testInput: String, testOutput: Any?) {
        val result = part1(testInput)
        assert(result == testOutput) {
            "\n${"-".repeat(80)}\nExpected:\n$testOutput\n${"-".repeat(80)}\nGot:\n$result\n${"-".repeat(80)}"
        }
    }

    fun solvePart1(input: String, expectedSolution: Any? = null) {
        val result = part1(input)
        println("Solution: $result")
        if (expectedSolution != null) {
            assert(result == expectedSolution) {
                "\n${"-".repeat(80)}\nExpected:\n$expectedSolution\n${"-".repeat(80)}\nGot:\n$result\n${"-".repeat(80)}"
            }
        }
    }

    fun testPart2(testInput: String, testOutput: Any?) {
        val result = part2(testInput)
        assert(result == testOutput) {
            "\n${"-".repeat(80)}\nExpected:\n$testOutput\n${"-".repeat(80)}\nGot:\n$result\n${"-".repeat(80)}"
        }
    }

    fun solvePart2(input: String, expectedSolution: Any? = null) {
        val result = part2(input)
        println("Solution: $result")
        if (expectedSolution != null) {
            assert(result == expectedSolution) {
                "\n${"-".repeat(80)}\nExpected:\n$expectedSolution\n${"-".repeat(80)}\nGot:\n$result\n${"-".repeat(80)}"
            }
        }
    }
}