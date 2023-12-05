# Advent of Code 2023

This repository contains my solutions for the [Advent of Code 2023](https://adventofcode.com/2023) challenge.

- [Input folder](./src/main/resources/inputs)
- [Test inputs folder](./src/main/resources/testInputs)

## Solutions

- [Open folder](./src/main/kotlin/days)

|                  Mo                   |                  Tu                   |                  We                   |                  Th                   |                  Fr                   |                  Sa                   |                  Su                   |
|:-------------------------------------:|:-------------------------------------:|:-------------------------------------:|:-------------------------------------:|:-------------------------------------:|:-------------------------------------:|:-------------------------------------:|
|                                       |                                       |                                       |                                       | [1](./src/main/kotlin/days/day01.kt)  | [2](./src/main/kotlin/days/day02.kt)  | [3](./src/main/kotlin/days/day03.kt)  |
| [4](./src/main/kotlin/days/day04.kt)  | [5](./src/main/kotlin/days/day05.kt)  | [6](./src/main/kotlin/days/day06.kt)  | [7](./src/main/kotlin/days/day07.kt)  | [8](./src/main/kotlin/days/day08.kt)  | [9](./src/main/kotlin/days/day09.kt)  | [10](./src/main/kotlin/days/day10.kt) |
| [11](./src/main/kotlin/days/day11.kt) | [12](./src/main/kotlin/days/day12.kt) | [13](./src/main/kotlin/days/day13.kt) | [14](./src/main/kotlin/days/day14.kt) | [15](./src/main/kotlin/days/day15.kt) | [16](./src/main/kotlin/days/day16.kt) | [17](./src/main/kotlin/days/day17.kt) |
| [18](./src/main/kotlin/days/day18.kt) | [19](./src/main/kotlin/days/day19.kt) | [20](./src/main/kotlin/days/day20.kt) | [21](./src/main/kotlin/days/day21.kt) | [22](./src/main/kotlin/days/day22.kt) | [23](./src/main/kotlin/days/day23.kt) | [24](./src/main/kotlin/days/day24.kt) |
| [25](./src/main/kotlin/days/day25.kt) |                                       |                                       |                                       |                                       |                                       |                                       |

## Daily todos

- [ ] Create `dayXX.kt` file in the [days folder](./src/main/kotlin/days)
- [ ] Paste this code in the file:

```kt
import utils.Puzzle

val dayXX = Puzzle(
    day = X,
    part1 = { input ->
        // Solve part 1 here
    },
    part2 = { input ->
        // Solve part 2 here
    },
)
```

- [ ] Add the tests for the day in the [Days file](./src/test/kotlin/Days.kt)

```kt
@Test
fun dayXX() {
    val testInput = AOC.getTestInputFromFile(X) ?: ""
    dayXX.testPart1(testInput, X)
    val input = AOC.getInput(X) ?: ""
    dayXX.solvePart1(input)

    val testInput2 = AOC.getTestInputFromFile(X, 2) ?: ""
    dayXX.testPart2(testInput2, X)
    dayXX.solvePart2(input)
}
```