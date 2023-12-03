package days

import utils.Puzzle

private data class Pos(
    val line: Int,
    val column: Int,
) {
    override fun toString(): String {
        return "(:${line + 1}:${column + 1})"
    }
}

private data class EnginePart(
    val content: String,
    val position: Pos,
) {
    val isSymbol = content.length == 1 && !content[0].isDigit()
    val isNumber = content[0].isDigit()
    val asInt = content.toIntOrNull() ?: -1

    fun getNearbyPositions(): List<Pos> {
        val positions = mutableListOf(
            // Left side
            Pos(position.line, position.column - 1),
            Pos(position.line - 1, position.column - 1),
            Pos(position.line + 1, position.column - 1),
            // Right side
            Pos(position.line, position.column + content.length),
            Pos(position.line - 1, position.column + content.length),
            Pos(position.line + 1, position.column + content.length),
        )

        for (i in content.indices) {
            positions.add(Pos(position.line - 1, position.column + i))
            positions.add(Pos(position.line + 1, position.column + i))
        }

        return positions
    }

    fun getNearbyPartsSimple(engine: List<EnginePart>): List<EnginePart> {
        return getNearbyPositions().mapNotNull { pos ->
            engine.find { it.position == pos }
        }
    }
}

private fun getEngineParts(input: String): List<EnginePart> {
    val lines = input.lines()
    val engineParts = mutableListOf<EnginePart>()

    lines.forEachIndexed { lineIndex, line ->
        line.forEachIndexed { columnIndex, char ->
            if (!char.isDigit()) {
                if (char != '.') engineParts.add(EnginePart(char.toString(), Pos(lineIndex, columnIndex)))
            }
            else if (columnIndex == 0 || !line[columnIndex - 1].isDigit()) {
                val number = Regex("""^\d+""").find(line.substring(columnIndex))?.value
                if (number != null) engineParts.add(EnginePart(number, Pos(lineIndex, columnIndex)))
            }
        }
    }

    return engineParts
}

val day03 = Puzzle(
    day = 3,
    part1 = { input ->
        val engineParts = getEngineParts(input)

        val importantEngineParts = engineParts.filter { part ->
            part.isNumber && part.getNearbyPartsSimple(engineParts).any { it.isSymbol }
        }

        return@Puzzle importantEngineParts.sumOf { it.asInt }
    },
    part2 = { input ->
        val engineParts = getEngineParts(input)

        val gears = engineParts.filter { part ->
            part.content == "*" && engineParts.filter { it.getNearbyPartsSimple(engineParts).contains(part) }.count { it.isNumber } == 2
        }

        val gearRatiosSum = gears.sumOf { gear ->
            val gearNumbers = engineParts.filter { it.getNearbyPartsSimple(engineParts).contains(gear) }
            gearNumbers[0].asInt * gearNumbers[1].asInt
        }

        return@Puzzle gearRatiosSum
    },
)