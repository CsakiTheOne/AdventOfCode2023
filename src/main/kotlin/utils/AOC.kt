package utils

import java.io.File

class AOC {
    companion object {

        fun getInput(day: Int): String? {
            val dayString = day.toString().padStart(2, '0')
            File("src/main/resources/inputs/day$dayString.txt").let { file ->
                if (file.exists()) {
                    return file.readText()
                } else {
                    file.createNewFile()
                    println("Input file for day $day not found. Created empty file.")
                }
            }
            return null
        }

        fun test(testInput: String, testOutput: String?, testFunction: (String) -> String?) {
            val result = testFunction(testInput)
            if (result == testOutput) {
                println("✅ Test passed.")
                return
            }
            throw Exception(
                "Test failed.\n${"-".repeat(80)}\nExpected:\n$testOutput\n${"-".repeat(80)}\nGot:\n$result\n${"-".repeat(80)}")
        }

    }
}