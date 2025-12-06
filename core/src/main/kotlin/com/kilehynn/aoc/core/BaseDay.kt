package com.kilehynn.aoc.core

abstract class BaseDay : Day {
    override val year: Int by lazy { inferYearFromPackage() }

    override val day: Int by lazy {
        this::class.java.simpleName.substring(3).toInt()
    }

    override var input: String = ""
    override var lines: List<String> = mutableListOf()

    override var expectedPart1: Long? = null
    override var expectedPart2: Long? = null

    protected abstract fun parse(input: String)

    override fun parseInput(s: String?) {
        input = (s
            ?: getInputFromClassLoader("inputs/$year/day$day.txt")).trim()
        lines = input.lines()
        parse(input)
    }

    private fun getInputFromClassLoader(inputPath : String) : String {
        return this::class.java
            .getResource("/inputs/$year/day$day.txt")
            ?.readText()             ?: error("Fichier d'input introuvable $inputPath")

    }


    private fun inferYearFromPackage(): Int {
        val pkg = this::class.java.`package`?.name
            ?: error("Class ${this::class.java.name} has no package, cannot infer year")

        // ex: com.kilehynn.aoc.y2024.days -> 2024
        val match = Regex("""y(\d{4})""").find(pkg)
            ?: error("Cannot infer year from package '$pkg' (expected yYYYY)")

        return match.groupValues[1].toInt()
    }

    fun printAndSolve() {
        this.parseInput()
        println("\u001B[34mDay $day (${year})\u001B[0m")

        val (part1, time1Ms) = measure { solvePart1() }
        val (part2, time2Ms) = measure { solvePart2() }

        val p1Status = expectedPart1?.let { expected ->
            if (part1 == expected) "\u001B[32mOK\u001B[0m"
            else {
                "\u001B[31mKO\u001B[0m (expected $expected)"
            }
        } ?: "\u001B[33mNo expected value\u001B[0m"


        val p2Status = expectedPart2?.let { expected ->
            if (part2 == expected) "\u001B[32mOK\u001B[0m"
            else {
                "\u001B[31mKO\u001B[0m (expected $expected)"
            }
        } ?: "\u001B[33mNo expected value\u001B[0m"

        println("\u001B[33mTime:\u001B[0m          Part 1: $time1Ms ms, Part 2: $time2Ms ms")
        println("\u001B[33mPart 1:\u001B[0m  $part1  [$p1Status]")
        println("\u001B[33mPart 2:\u001B[0m  $part2  [$p2Status]")
    }

}
