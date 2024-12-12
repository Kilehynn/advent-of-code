package days

import Day
import main

class Day2 : Day {
    private var report : List<List<Int>> = getInput().split(System.lineSeparator()).map {
        it.split(Regex("\\s+")).map { value -> value.toInt() }
    }

    override fun solvePart1(debug: Boolean): Int {
        return report.count {
            isSafe(it)
        }
    }

    private fun isSafe(report: List<Int>): Boolean {
        val diffList = report.zipWithNext().map { (a,b) -> b - a }
        return diffList.all { it in 1..3 } || diffList.all { it in -3..-1 }
    }

    override fun solvePart2(debug: Boolean): Int {
        return report.count{
            report.indices.any { i ->
                isSafe(it.filterIndexed() { index, _ -> index != i })
            }
        }
    }


}