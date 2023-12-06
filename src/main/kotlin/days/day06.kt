package days

import utils.Puzzle

private data class Race(
    val duration: Int,
    val recordDistance: Int,
)

private class Boat {
    companion object {
        fun move(race: Race, chargingTime: Int): Int {
            return (race.duration - chargingTime) * chargingTime
        }

        fun isWinningRace(race: Race, chargingTime: Int): Boolean {
            return move(race, chargingTime) > race.recordDistance
        }
    }
}

val day06 = Puzzle(
    day = 6,
    part1 = { input ->
        val lines = input.lines()
        val times = lines[0]
            .substringAfter("Time:")
            .split(" ")
            .mapNotNull { it.trim().toIntOrNull() }
        val distances = lines[1]
            .substringAfter("Distancce:")
            .split(" ")
            .mapNotNull { it.trim().toIntOrNull() }
        val races = times.indices.associate { times[it] to distances[it] }.map { Race(it.key, it.value) }
        val margins = races.map { race ->
            val min = race.duration / 6
            val max = race.duration * 5 / 6
            (min..max).count { chargeTime -> Boat.isWinningRace(race, chargeTime) }
        }
        return@Puzzle margins.reduce(Int::times)
    },
    part2 = { input ->
        val lines = input.lines()
        val time = lines[0].substringAfter("Time:").replace(" ", "").toLong()
        val distance = lines[1].substringAfter("Distance:").replace(" ", "").toLong()
        println("time: $time, distance: $distance")
        return@Puzzle 0
    },
)