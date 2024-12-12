package days

import Day
import kotlin.math.abs

class Day1: Day {
    val leftColumn = ArrayList<Long>()
    val rightColumn = ArrayList<Long>()
    val rightOccurrence = HashMap<Long, Long>()

    init{
        getInput().split(System.lineSeparator()).map {
            it.split(Regex("\\s+"))
        }.forEach {
            leftColumn.add(it[0].toLong())
            rightColumn.add(it[1].toLong())
            rightOccurrence.putIfAbsent(it[1].toLong(), 0)
            rightOccurrence[it[1].toLong()] = rightOccurrence[it[1].toLong()]!! + 1
        }
        leftColumn.sort()
        rightColumn.sort()
    }

    override fun solvePart1(debug: Boolean): Long {
        return leftColumn.zip(rightColumn).sumOf {
            (left, right) -> abs(left - right)
        }
    }

    override fun solvePart2(debug: Boolean): Long {
        return leftColumn.sumOf { it * (rightOccurrence[it]?:0) }
    }
}