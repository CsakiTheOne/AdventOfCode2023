package days

import utils.Puzzle
import kotlin.math.pow

private data class Card(
    val id: Int,
    val winningNumbers: Set<Int>,
    val yourNumbers: Set<Int>,
    var count: Int = 1,
) {
    private var matchingNumbersCountCache: Int? = null

    fun getMatchingNumbers(): Set<Int> {
        return winningNumbers.intersect(yourNumbers)
    }

    fun getMatchingNumbersCount(): Int {
        if (matchingNumbersCountCache != null) {
            return matchingNumbersCountCache!!
        }
        val matchingNumbers = getMatchingNumbers()
        matchingNumbersCountCache = matchingNumbers.size
        return matchingNumbersCountCache!!
    }

    companion object {

        fun fromString(input: String): Card {
            // Card 1: 29 62 36  3 11 | 75 48  4 37 10
            val id = Regex("""\d+""").find(input)?.value?.toIntOrNull() ?: 0
            val numbers = input.substringAfter(":").split("|")
            val winningNumbers = numbers[0]
                .split(" ")
                .mapNotNull { it.trim().toIntOrNull() }
            val yourNumbers = numbers[1]
                .split(" ")
                .mapNotNull { it.trim().toIntOrNull() }
            return Card(id, winningNumbers.toSet(), yourNumbers.toSet())
        }

    }
}

val day04 = Puzzle(
    day = 4,
    part1 = { input ->
        val cards = input
            .split("\n")
            .filter { it.isNotBlank() }
            .map { Card.fromString(it) }

        val points = cards.map { card ->
            val matchingNumbers = card.getMatchingNumbers()
            if (matchingNumbers.isEmpty()) {
                return@map 0
            }
            2f.pow(matchingNumbers.size - 1).toInt()
        }

        return@Puzzle points.sum()
    },
    part2 = { input ->
        val cards = input
            .split("\n")
            .filter { it.isNotBlank() }
            .associate {
                val card = Card.fromString(it)
                card.id to card
            }
            .toMutableMap()

        val maxId = cards.keys.max()
        for (i in 1..maxId) {
            val card = cards[i] ?: continue
            val matchingNumbers = card.getMatchingNumbersCount()
            val idsToCopy = card.id + 1 .. card.id + matchingNumbers
            idsToCopy.forEach { id ->
                cards[id] = cards[id]?.copy(count = cards[id]!!.count + card.count)
                    ?: return@Puzzle 0
            }
        }

        return@Puzzle cards.values.sumOf { it.count }
    },
)