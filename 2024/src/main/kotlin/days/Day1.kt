package days

import Day
import kotlin.math.abs

class Day1: Day {
    val leftColumn = ArrayList<Int>()
    val rightColumn = ArrayList<Int>()
    val rightOccurrence = HashMap<Int, Int>()

    override fun solvePart1(input: String): String {
        parseInputPart1(input)
        return leftColumn.zip(rightColumn).sumOf {
            (left, right) -> abs(left - right)
        }.toString()
    }

    private fun parseInputPart1(input: String) {
        input.split(System.lineSeparator()).map {
            it.split(Regex("\\s+"))
        }.forEach {
            leftColumn.add(it[0].toInt())
            rightColumn.add(it[1].toInt())
        }
        leftColumn.sort()
        rightColumn.sort()
    }

    override fun solvePart2(input: String): String {
        leftColumn.clear()
        parseInputPart2(input)
        return leftColumn.sumOf { it * (rightOccurrence[it]?:0) }.toString()

    }

    private fun parseInputPart2(input: String) {
        input.split(System.lineSeparator()).map {
            it.split(Regex("\\s+")).toList()
        }.forEach() {
            leftColumn.add(it[0].toInt())
            rightOccurrence.putIfAbsent(it[1].toInt(), 0)
            rightOccurrence[it[1].toInt()] = rightOccurrence[it[1].toInt()]!! + 1
        }
    }
}