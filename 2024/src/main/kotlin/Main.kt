import days.*


fun main(args: Array<String>) {
    if (args.isNotEmpty() && args[0] == "-all") {
        // run each day and add a separator between them
        println("=".repeat(50))
        days.forEach {
            solveAndPrintDay(it)
            println("=".repeat(50))
        }
    } else {
        solveAndPrintDay(days.last())
    }
}

fun solveAndPrintDay(day: Day) {
    val dayNumber = day.getDay().toString()

    println("\u001B[34mDay $dayNumber\u001B[0m")

    // time how long it takes to solve part 1
    var start = System.currentTimeMillis()
    val part1 = day.solvePart1()
    var end = System.currentTimeMillis()
    val part1Time = end - start

    start = System.currentTimeMillis()
    val part2 = day.solvePart2()
    end = System.currentTimeMillis()
    val part2Time = end - start

    println("\u001B[33mTime:\u001B[0m${" ".repeat(10)}Part 1: $part1Time ms, Part 2: $part2Time ms")
    println("\u001B[33mPart 1:\u001B[0m${" ".repeat(10)}$part1")
    println("\u001B[33mPart 2:\u001B[0m${" ".repeat(10)}$part2")
}

val days = listOf(
    Day1(),
    Day2(),
    Day3(),
    Day4(),
    Day5()
)