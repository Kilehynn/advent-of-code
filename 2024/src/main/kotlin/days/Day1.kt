package days

import Day
import kotlin.math.abs

class Day1: Day {
    val leftColumn = ArrayList<Int>()
    val rightColumn = ArrayList<Int>()
    val rightOccurrence = HashMap<Int, Int>()

    init{
        getInput().split(System.lineSeparator()).map {
            it.split(Regex("\\s+"))
        }.forEach {
            leftColumn.add(it[0].toInt())
            rightColumn.add(it[1].toInt())
            rightOccurrence.putIfAbsent(it[1].toInt(), 0)
            rightOccurrence[it[1].toInt()] = rightOccurrence[it[1].toInt()]!! + 1
        }
        leftColumn.sort()
        rightColumn.sort()
    }

    override fun solvePart1(): Int {
        return leftColumn.zip(rightColumn).sumOf {
            (left, right) -> abs(left - right)
        }
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

    override fun solvePart2(): Int {
        return leftColumn.sumOf { it * (rightOccurrence[it]?:0) }
    }
}