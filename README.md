# Advent of Code 2023

This repository contains my solutions for the [Advent of Code 2023](https://adventofcode.com/2023) challenge.

- [Solutions folder](./src/main/kotlin/days)
  - [Day 1](./src/main/kotlin/days/day01.kt)
  - [Day 2](./src/main/kotlin/days/day02.kt)
- [Input folder](./src/main/resources/inputs)
- [Test inputs folder](./src/main/resources/testInputs)

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