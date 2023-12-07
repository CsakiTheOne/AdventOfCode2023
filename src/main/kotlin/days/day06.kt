package days

import utils.Puzzle
import java.lang.Math.pow
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.roundToLong
import kotlin.math.sqrt

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

        // (duration - chargingTime) * chargingTime = recordDistance
        // chargingTime^2 - duration * chargingTime + recordDistance = 0
        // chargingTime = (duration +- sqrt(duration^2 - 4 * recordDistance)) / 2
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
        val minChargingTime = (time - sqrt(time.toDouble().pow(2) - 4.0 * distance)) / 2.0
        val maxChargingTime = (time + sqrt(time.toDouble().pow(2) - 4.0 * distance)) / 2.0
        println("minChargingTime: $minChargingTime, maxChargingTime: $maxChargingTime")
        val chargeTimes = maxChargingTime - minChargingTime + 1
        println("chargeTimes: $chargeTimes")
        return@Puzzle chargeTimes.toLong()
    },
)