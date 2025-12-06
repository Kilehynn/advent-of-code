package com.kilehynn.aoc.y2025.days

import com.kilehynn.aoc.core.BaseDay


class Day3 : BaseDay() {
    var banks: List<ArrayList<Long>> = listOf()

    override var expectedPart1: Long? = 17034L
    override var expectedPart2: Long? = 168798209663590L

    override fun parse(input: String) {
        banks = lines.map { bank ->
                val batteries = ArrayList<Long>()
                for (batterie in bank) {
                    batteries.add(batterie.toString().toLong())
                }
                return@map batteries
            }
    }

    override fun solvePart1(debug: Boolean): Long {
        return banks.sumOf { bank ->
            val maxValue = bank.subList(0, bank.size - 1).max()
            val maxValueIndex = bank.indexOf(maxValue)
            val secondMaxValue = bank.subList(maxValueIndex + 1, bank.size).max()

            val result = (maxValue.toString() + secondMaxValue.toString()).toLong()
            result
        }
    }

    override fun solvePart2(debug: Boolean): Long {
        return banks.sumOf { bank ->
            var result = ""
            var currentBank = bank
            while (result.length < 12) {
                val searchArea = currentBank.subList(0, currentBank.size - (12 - result.length) + 1)
                val maxValue = searchArea.max()
                val maxValueIndex = currentBank.indexOf(maxValue)
                currentBank = ArrayList(currentBank.subList(maxValueIndex + 1, currentBank.size))
                result += maxValue
            }
            result.toLong()
        }
    }
}
