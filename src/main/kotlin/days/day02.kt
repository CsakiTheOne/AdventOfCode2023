package days

import utils.Puzzle

private data class Pull(
    val cubes: Map<String, Int>,
) {
    val red = cubes["red"] ?: 0
    val green = cubes["green"] ?: 0
    val blue = cubes["blue"] ?: 0

    companion object {
        fun fromString(input: String): Pull {
            val cubes = input.split(", ").associate { cubeString ->
                val (count, color) = cubeString.split(" ")
                color to count.toInt()
            }
            return Pull(cubes)
        }
    }
}

private data class Game(
    val id: Int,
    val pulls: List<Pull>,
) {
    val reds = pulls.map { it.red }
    val greens = pulls.map { it.green }
    val blues = pulls.map { it.blue }

    companion object {
        fun fromString(input: String): Game {
            // Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            val (idString, pullsString) = input.split(": ")
            val id = idString.split(" ")[1].toInt()
            val pulls = pullsString.split("; ").map { pullString ->
                Pull.fromString(pullString)
            }
            return Game(id, pulls)
        }
    }
}

val day02pt1 = Puzzle(
    day = 2,
    part = 1,
) { input ->
    val games = input.lines().map { Game.fromString(it) }

    val maxRed = 12
    val maxGreen = 13
    val maxBlue = 14

    val possibleGames = games.filter { game ->
        game.reds.all { it <= maxRed } &&
                game.greens.all { it <= maxGreen } &&
                game.blues.all { it <= maxBlue }
    }

    return@Puzzle possibleGames.sumOf { it.id }
}

val day02pt2 = Puzzle(
    day = 2,
    part = 2,
) { input ->
    val games = input.lines().map { Game.fromString(it) }

    val powers = games.map { game ->
        val minRed = game.reds.max()
        val minGreen = game.greens.max()
        val minBlue = game.blues.max()
        minRed * minGreen * minBlue
    }

    return@Puzzle powers.sum()
}