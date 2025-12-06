package com.kilehynn.aoc.core

interface Day {
    val year: Int
    val day: Int

    var input: String
    var lines: List<String>

    var expectedPart1: Long?
    var expectedPart2: Long?

    fun solvePart1(debug: Boolean = false): Long
    fun solvePart2(debug: Boolean = false): Long


    fun parseInput(s: String? = null)

}
