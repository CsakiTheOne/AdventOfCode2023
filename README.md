# Advent of Code 2023

This repository contains my solutions for the [Advent of Code 2023](https://adventofcode.com/2023) challenge.

- [Solutions folder](./src/main/kotlin/days)
  - [Day 1](./src/main/kotlin/days/day01.kt)
- [Input folder](./src/main/resources/inputs)
- [Test inputs folder](./src/main/resources/testInputs)

## Daily todos

- [ ] Create `dayXX.kt` file in the [days folder](./src/main/kotlin/days)
- [ ] Paste this code in the file:

```kt
import utils.Puzzle

val dayXXpt1 = Puzzle(
    day = X,
    part = 1,
) { input ->
    // Solve part 1 here
}

val dayXXpt2 = Puzzle(
    day = X,
    part = 2,
) { input ->
    // Solve part 2 here
}
```

- [ ] Add the tests for the day in the [Days file](./src/test/kotlin/Days.kt)

```kt
@Test
fun dayXX() {
    val testInput = AOC.getTestInputFromFile(X) ?: ""
    dayXXpt1.test(testInput, X)
    val input = AOC.getInput(X) ?: ""
    dayXXpt1.solve(input)

    val testInput2 = AOC.getTestInputFromFile(X, 2) ?: ""
    dayXXpt2.test(testInput2, X)
    dayXXpt2.solve(input)
}
```