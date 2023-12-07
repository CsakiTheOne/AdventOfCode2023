package days

import utils.Puzzle
import kotlin.math.pow

private data class CamelCard(
    val sign: String,
) {
    val strength = when (sign) {
        "T" -> 10
        "J" -> 11
        "Q" -> 12
        "K" -> 13
        "A" -> 14
        "*" -> 1
        else -> sign.toIntOrNull() ?: throw IllegalArgumentException("Invalid sign: $sign")
    }

    val sortingStrength = if (sign == "*") 11 else strength

    override fun toString(): String {
        return sign
    }

    companion object {
        val signs = listOf("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K")
    }
}

private data class HandType(
    val name: String,
    val strength: Int,
    val predicate: (Hand) -> Boolean,
) {
    companion object {
        val FIVE_OF_A_KIND = HandType("Five of a Kind", 10) { hand ->
            hand.cards.map { it.sign }.toSet().size == 1
        }
        val FOUR_OF_A_KIND = HandType("Four of a Kind", 9) { hand ->
            val signs = hand.cards.groupBy { it.sign }
            signs.values.any { it.size == 4 }
        }
        val FULL_HOUSE = HandType("Full House", 8) { hand ->
            val signs = hand.cards.groupBy { it.sign }
            signs.values.any { it.size == 3 } && signs.values.any { it.size == 2 }
        }
        val THREE_OF_A_KIND = HandType("Three of a Kind", 7) { hand ->
            val signs = hand.cards.groupBy { it.sign }
            signs.values.any { it.size == 3 }
        }
        val TWO_PAIRS = HandType("Two Pairs", 6) { hand ->
            val signs = hand.cards.groupBy { it.sign }
            signs.values.count { it.size == 2 } == 2
        }
        val ONE_PAIR = HandType("One Pair", 5) { hand ->
            val signs = hand.cards.groupBy { it.sign }
            signs.values.count { it.size == 2 } == 1
        }
        val HIGH_CARD = HandType("High Card", 4) { true }

        val values = listOf(
            FIVE_OF_A_KIND,
            FOUR_OF_A_KIND,
            FULL_HOUSE,
            THREE_OF_A_KIND,
            TWO_PAIRS,
            ONE_PAIR,
        ).sortedByDescending { it.strength }
    }
}

private data class Hand(
    val cards: List<CamelCard>,
    val bid: Int,
) {
    constructor(cards: String, bid: Int) : this(
        cards.split("").filter { it.isNotBlank() }.map { CamelCard(it) },
        bid,
    )

    val handType = HandType.values.firstOrNull { it.predicate(this) } ?: HandType.HIGH_CARD
    val strength = handType.strength + cards.mapIndexed { index, card ->
        card.sortingStrength * 100.0.pow(1 - index)
    }.sum() / 100_000.0

    fun getJokeredStrength(): Double {
        val cardsString = cards.joinToString("") { it.sign }
        if (!cardsString.contains("*")) return strength
        val actualSigns = cards.map { it.sign }.toSet() - "*" + "A"
        val handStrings = mutableSetOf<String>()
        val baseHandStrings = mutableSetOf<String>()
        val jokerIndices = cardsString
            .mapIndexedNotNull { index, c -> if (c == '*') index else null }
        actualSigns.forEach { sign ->
            handStrings.add(cardsString.replace("*", sign))
            baseHandStrings.add(cardsString.replace("*", sign))
        }
        for (i in jokerIndices) {
            actualSigns.forEach { sign ->
                baseHandStrings.forEach { baseHandString ->
                    handStrings.add(baseHandString.replaceRange(i, i + 1, sign))
                }
            }
        }
        val hands = handStrings.map { Hand(it, bid) }
        println("Base: $cardsString\n${hands.joinToString("\n") { "- ${it.cards.joinToString("")} ${it.strength}" }}")
        return hands.maxOf { it.strength }
    }

    override fun toString(): String {
        return "H(${cards.joinToString("") { it.sign }}, bid=$bid, handType=${handType.name}, strength=$strength)"
    }
}

val day07 = Puzzle(
    day = 7,
    part1 = { input ->
        val hands = input
            .lines()
            .filter { it.isNotBlank() }
            .map { line ->
                val cards = line.substringBefore(" ")
                val bid = line.substringAfter(" ").toInt()
                Hand(cards, bid)
            }
        val sorted = hands.sortedBy { it.strength }

        return@Puzzle sorted
            .mapIndexed { index, hand -> hand.bid * (index + 1) }
            .sum()
    },
    part2 = { input ->
        val hands = input
            .replace("J", "*")
            .lines()
            .filter { it.isNotBlank() }
            .map { line ->
                val cards = line.substringBefore(" ")
                val bid = line.substringAfter(" ").toInt()
                Hand(cards, bid)
            }
        val sorted = hands.sortedBy { it.getJokeredStrength() }

        return@Puzzle sorted
            .mapIndexed { index, hand -> hand.bid * (index + 1L) }
            .sum()
    },
)