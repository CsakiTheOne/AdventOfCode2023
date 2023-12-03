package days

import utils.Puzzle
import utils.Utils.Companion.expand

private data class Pos(
    val line: Int,
    val start: Int,
    val end: Int,
) {
    val range = start..end

    override fun toString(): String {
        return "(L:$line;C:$start..$end)"
    }
}

private data class EnginePart(
    val content: String,
    val position: Pos,
) {
    val isSymbol = content.length == 1 && !content[0].isDigit()
    val isNumber = content[0].isDigit()
    val asInt = content.toIntOrNull() ?: -1

    fun getNearbyParts(engine: List<EnginePart>): List<EnginePart> {
        val (line, start, end) = position

        val nearLineParts = engine.filter { ((line - 1)..(line + 1)).contains(it.position.line) }

        val left = nearLineParts.filter { it.position.range.expand(1).contains(start) }
        val right = nearLineParts.filter { it.position.range.expand(1).contains(end) }

        return (left + right).distinct() - this
    }
}

private fun getEngineParts(input: String): List<EnginePart> {
    val lines = input.lines()
    val engineParts = mutableListOf<EnginePart>()

    lines.forEachIndexed { lineIndex, line ->
        line.forEachIndexed { columnIndex, char ->
            if (!char.isDigit()) {
                if (char != '.') engineParts.add(EnginePart(char.toString(), Pos(lineIndex, columnIndex, columnIndex)))
            }
            else if (columnIndex == 0 || !line[columnIndex - 1].isDigit()) {
                val number = Regex("""^\d+""").find(line.substring(columnIndex))?.value
                if (number != null) engineParts.add(EnginePart(number, Pos(lineIndex, columnIndex, columnIndex + number.length - 1)))
            }
        }
    }

    return engineParts
}

val day03 = Puzzle(
    day = 3,
    part1 = { input ->
        val engine = getEngineParts(input)

        val importantEngineParts = engine.filter { part ->
            part.isNumber && part.getNearbyParts(engine).any { it.isSymbol }
        }

        return@Puzzle importantEngineParts.sumOf { it.asInt }
    },
    part2 = { input ->
        val engine = getEngineParts(input)

        val gears = engine.filter { part ->
            part.content == "*" && part.getNearbyParts(engine).count { it.isNumber } == 2
        }

        val gearRatiosSum = gears.sumOf { gear ->
            val gearNumbers = gear.getNearbyParts(engine).filter { it.isNumber }
            gearNumbers[0].asInt * gearNumbers[1].asInt
        }

        return@Puzzle gearRatiosSum
    },
)