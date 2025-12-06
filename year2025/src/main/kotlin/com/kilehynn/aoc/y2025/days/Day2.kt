package com.kilehynn.aoc.y2025.days

import com.kilehynn.aoc.core.BaseDay


class Day2 : BaseDay() {

    override var expectedPart1: Long? = 12586854255L
    override var expectedPart2: Long? = 17298174201L

    var ranges: List<Pair<Long, Long>> = listOf()
    override fun parse(input: String) {
        ranges = input.split(",").map {
            val split = it.split("-", limit = 2)
            Pair(split[0].toLong(), split[1].toLong())
        }.map { it.first to it.second }.toList()
    }

    override fun solvePart1(debug: Boolean): Long {
        return ranges.sumOf {
            var invalid: Long = 0
            for (i in it.first..it.second) {
                val str = i.toString()
                if (str.take(str.length / 2) == str.substring(str.length / 2)) {
                    invalid += i
                }
            }
            invalid
        }
    }

    override fun solvePart2(debug: Boolean): Long {
        return ranges.sumOf {
            var invalid: Long = 0
            for (i in it.first..it.second) {
                val str = i.toString()
                val possiblePatternSize = ArrayList<Int>()
                for (j in 1..<str.length) {
                    if (str.length / j >= 2)
                        possiblePatternSize.add(j)
                }
                for (patternSize in possiblePatternSize) {
                    if (str.matches(Regex("^(${str.take(patternSize)})+$"))) {
                        invalid += i
                        break
                    }
                }
            }
            invalid
        }
    }
}
