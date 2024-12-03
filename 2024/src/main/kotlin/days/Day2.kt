package days

import Day
import main

class Day2 : Day {

    override fun solvePart1(input: String): String {
        val parsedInput = parse(input)
        return parsedInput.count {
            isSafe(it)
        }.toString()
    }

    private fun parse(input: String): List<List<Int>> {
        return input.split(System.lineSeparator()).map {
            it.split(Regex("\\s+")).map { value -> value.toInt() }
        }
    }
    private fun isSafe(report: List<Int>): Boolean {
        val diffList = report.zipWithNext().map { (a,b) -> b - a }
        return diffList.all { it in 1..3 } || diffList.all { it in -3..-1 }
    }

    override fun solvePart2(input: String): String {
        val parsedInput = parse(input)
        return parsedInput.count{
            parsedInput.indices.any { i ->
                isSafe(it.filterIndexed() { index, _ -> index != i })
            }
        }.toString()
    }


}