package utils

class Puzzle(
    val day: Int,
    val part: Int,
    val code: Puzzle.(input: String) -> Any?,
) {
    fun test(testInput: String, testOutput: Any?) {
        val result = code(testInput)
        assert(result == testOutput) {
            "\n${"-".repeat(80)}\nExpected:\n$testOutput\n${"-".repeat(80)}\nGot:\n$result\n${"-".repeat(80)}"
        }
    }

    fun solve(input: String): Any? {
        val result = code(input)
        println("Solution: $result")
        return result
    }
}