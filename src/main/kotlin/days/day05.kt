package days

import utils.Puzzle

private data class Converter(
    val sourceName: String,
    val destinationName: String,
    val sourceRangeStart: Long,
    val destinationRangeStart: Long,
    val rangeLength: Long,
) {
    val destination = destinationRangeStart..<destinationRangeStart + rangeLength
    val source = sourceRangeStart..<sourceRangeStart + rangeLength

    fun convert(input: Long): Long {
        if (input !in source) {
            return input
        }
        return input - sourceRangeStart + destinationRangeStart
    }

    fun convertRange(input: LongRange): Set<LongRange> {
        val validRange = source.intersect(input)
        val invalidRange = source.subtract(input)
        if (validRange.isEmpty()) {
            return setOf(input)
        }
        val convertedRange = validRange.map { convert(it) }
        return setOf(
            convertedRange.first()..convertedRange.last(),
            invalidRange.first()..invalidRange.last(),
        )
    }
}

val day05 = Puzzle(
    day = 5,
    part1 = { input ->
        val seeds = input
            .lines()
            .first()
            .removePrefix("seeds: ")
            .split(" ")
            .map { it.toLong() }
        val converters = mutableListOf<Converter>()

        fun convert(value: Long, sourceName: String, destinationName: String): Long {
            val sourceConverters = converters
                .filter { it.sourceName.equals(sourceName, true) }
            if (sourceConverters.isEmpty()) {
                throw Exception("No converters found for $sourceName")
            }
            if (sourceConverters.first().destinationName != destinationName) {
                return convert(
                    sourceConverters.firstOrNull { it.source.contains(value) }?.convert(value) ?: value,
                    sourceConverters.first().destinationName,
                    destinationName,
                )
            }
            return sourceConverters.firstOrNull { it.source.contains(value) }?.convert(value) ?: value
        }

        val inputChunks = input
            .split("\n\n", "\r\n\r\n")
            .drop(1)

        inputChunks.forEach { chunk ->
            val lines = chunk.lines()
            val (sourceName, _, destinationName) = lines.first().split("-", " ")
            lines
                .drop(1)
                .filter { it.isNotBlank() }
                .forEach { line ->
                    val (destinationStart, sourceStart, length) = line
                        .split(" ")
                        .mapNotNull { it.trim().toLongOrNull() }
                    converters.add(
                        Converter(
                            sourceName,
                            destinationName,
                            sourceStart,
                            destinationStart,
                            length,
                        )
                    )
                }
        }

        if (converters.isEmpty()) {
            throw Exception("Failed to create converters")
        }

        val locations = seeds.map { convert(it, "seed", "location") }

        return@Puzzle locations.min()
    },
    part2 = { input ->
        //TODO: Day 5 part 2
        val seedRangeValues = input
            .lines()
            .first()
            .removePrefix("seeds: ")
            .split(" ")
            .map { it.toLong() }
        val seedRangeStarts = seedRangeValues
            .filterIndexed { index, _ -> index % 2 == 0 }
        val seedRangeLengths = seedRangeValues
            .filterIndexed { index, _ -> index % 2 == 1 }
        val seedRanges = seedRangeStarts
            .zip(seedRangeLengths)
            .map { it.first..<(it.first + it.second) }
        val converters = mutableListOf<Converter>()

        val inputChunks = input
            .split("\n\n", "\r\n\r\n")
            .drop(1)

        inputChunks.forEach { chunk ->
            val lines = chunk.lines()
            val (sourceName, _, destinationName) = lines.first().split("-", " ")
            lines
                .drop(1)
                .filter { it.isNotBlank() }
                .forEach { line ->
                    val (destinationStart, sourceStart, length) = line
                        .split(" ")
                        .mapNotNull { it.trim().toLongOrNull() }
                    converters.add(
                        Converter(
                            sourceName,
                            destinationName,
                            sourceStart,
                            destinationStart,
                            length,
                        )
                    )
                }
        }

        if (converters.isEmpty()) {
            throw Exception("Failed to create converters")
        }

        return@Puzzle 0//part1(newInput)
    },
)