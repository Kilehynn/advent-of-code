package days

import Day
import main

class Day2 : Day {
    private var report : List<List<Long>> = getInput().split(System.lineSeparator()).map {
        it.split(Regex("\\s+")).map { value -> value.toLong() }
    }

    override fun solvePart1(debug: Boolean): Long {
        return report.count {
            isSafe(it)
        }.toLong()
    }

    private fun isSafe(report: List<Long>): Boolean {
        val diffList = report.zipWithNext().map { (a,b) -> b - a }
        return diffList.all { it in 1..3 } || diffList.all { it in -3..-1 }
    }

    override fun solvePart2(debug: Boolean): Long {
        return report.count{
            report.indices.any { i ->
                isSafe(it.filterIndexed() { index, _ -> index != i })
            }
        }.toLong()
    }


}