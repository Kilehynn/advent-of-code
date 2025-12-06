package com.kilehynn.aoc.y2024.days

import com.kilehynn.aoc.core.BaseDay
import kotlin.math.abs

class Day1 : BaseDay() {
    val leftColumn = mutableListOf<Long>()
    val rightColumn = mutableListOf<Long>()
    val rightOccurrence = HashMap<Long, Long>()

    override var expectedPart1:Long? = 1660292L
    override var expectedPart2:Long? = 22776016L

    override fun parse(input: String) {
        this.input = input
        lines  = input.lines()
        lines.map {
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
        return leftColumn.zip(rightColumn).sumOf { (left, right) ->
            abs(left - right)
        }
    }

    override fun solvePart2(debug: Boolean): Long {
        return leftColumn.sumOf { it * (rightOccurrence[it] ?: 0) }
    }

}
