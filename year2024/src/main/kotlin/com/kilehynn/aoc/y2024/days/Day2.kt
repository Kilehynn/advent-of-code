package com.kilehynn.aoc.y2024.days

import com.kilehynn.aoc.core.BaseDay


class Day2 : BaseDay() {
    private var report: List<List<Long>> = mutableListOf()

    override var expectedPart1: Long? = 534L
    override var expectedPart2: Long? = 577L

    override fun parse(input: String) {
        this.input = input
        lines = input.lines()
        report = lines.map {
            it.split(Regex("\\s+")).map { value -> value.toLong() }
        }
    }

    override fun solvePart1(debug: Boolean): Long {
        return report.count {
            isSafe(it)
        }.toLong()
    }

    private fun isSafe(report: List<Long>): Boolean {
        val diffList = report.zipWithNext().map { (a, b) -> b - a }
        return diffList.all { it in 1..3 } || diffList.all { it in -3..-1 }
    }

    override fun solvePart2(debug: Boolean): Long {
        return report.count {
            report.indices.any { i ->
                isSafe(it.filterIndexed { index, _ -> index != i })
            }
        }.toLong()
    }


}
