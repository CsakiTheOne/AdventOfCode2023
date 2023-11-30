package utils

import java.io.File

class AOC {
    companion object {

        /**
         * Returns the input for the given day.
         * If the file does not exist, the input will be fetched from the Advent of Code website.
         * @param day The day for which the input should be returned. The file name is dayXX.txt
         */
        fun getInput(day: Int): String? {
            return getInputFromFile(day) ?: getInputFromWeb(day, downloadInput = true)
        }

        /**
         * Returns the test input for the given day.
         * If the file does not exist, the input will be fetched from the Advent of Code website.
         * @param day The day for which the input should be returned. The file name is dayXX.txt
         * @param part The part for which the input should be returned. The file name is dayXXptX.txt
         */
        fun getTestInputFromFile(day: Int, part: Int? = null): String? {
            val dayString = day.toString().padStart(2, '0')
            val partString = if (part != null) "pt$part" else ""
            val path = "src/main/resources/testInputs/day$dayString$partString.txt"
            File(path).let { file ->
                if (file.exists()) {
                    return file.readText()
                } else {
                    file.parentFile.mkdirs()
                    file.createNewFile()
                    println("Input file for day $day part $part not found. Created empty file.")
                }
            }
            return null
        }

        /**
         * Returns the input for the given day.
         * If the input file does not exist, it will be created.
         * @param day The day for which the input should be returned. The file name is dayXX.txt
         */
        fun getInputFromFile(day: Int): String? {
            val dayString = day.toString().padStart(2, '0')
            val path = "src/main/resources/inputs/day$dayString.txt"
            File(path).let { file ->
                if (file.exists()) {
                    return file.readText()
                } else {
                    file.parentFile.mkdirs()
                    file.createNewFile()
                    println("Input file for day $day not found. Created empty file.")
                }
            }
            return null
        }

        /**
         * Returns the input for the given day from the Advent of Code website.
         * @param day The day for which the input should be returned.
         * @param year The year for which the input should be returned.
         */
        fun getInputFromWeb(day: Int, year: Int = 2023, downloadInput: Boolean = false): String? {
            val path = "https://adventofcode.com/$year/day/$day/input"
            val request = okhttp3.Request.Builder()
                .url(path)
                .header("Cookie", "session=${Secret.SESSION_ID}")
                .build()
            val response = okhttp3.OkHttpClient().newCall(request).execute()
            val input = response.body?.string()
            if (downloadInput && input != null) {
                saveInput(day, input)
            }
            return input
        }

        private fun saveInput(day: Int, input: String) {
            val dayString = day.toString().padStart(2, '0')
            val path = "src/main/resources/inputs/day$dayString.txt"
            File(path).writeText(input)
        }

    }
}