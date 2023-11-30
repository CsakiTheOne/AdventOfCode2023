package utils

import java.io.File

class AOC {
    companion object {

        fun getInput(day: Int): String? {
            val dayString = day.toString().padStart(2, '0')
            val path = "src/main/resources/inputs/day$dayString.txt"
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

        fun getInputFromWeb(day: Int, year: Int = 2023): String? {
            val path = "https://adventofcode.com/$year/day/$day/input"
            val request = okhttp3.Request.Builder()
                .url(path)
                .header("Cookie", "session=${Secret.SESSION_ID}")
                .build()
            val response = okhttp3.OkHttpClient().newCall(request).execute()
            return response.body?.string()
        }

    }
}