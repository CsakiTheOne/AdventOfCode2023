package utils

import java.io.File

class AOC {
    companion object {

        fun getInput(day: Int, part: Int? = null): String? {
            val dayString = day.toString().padStart(2, '0')
            val path = "src/main/resources/inputs/day$dayString${if (part != null) "pt$part" else ""}.txt"
            File(path).let { file ->
                if (file.exists()) {
                    return file.readText()
                } else {
                    file.createNewFile()
                    println("Input file for day $day not found. Created empty file.")
                }
            }
            return null
        }

    }
}