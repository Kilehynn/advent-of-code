package com.kilehynn.aoc.y2025.days

import com.kilehynn.aoc.core.BaseDay
import kotlin.math.max
import kotlin.math.min

class Day5 : BaseDay() {

    override var expectedPart1: Long? = 707L
    override var expectedPart2: Long? = 361615643045059L

    var ingredientDatabase: String = ""
    var ranges: ArrayList<LongRange> = ArrayList()
    var ingredients: List<Long> = listOf()

    override fun solvePart1(debug: Boolean): Long {
        var freshIngredients = 0L

        for (ingredient in ingredients) {
            for (range in ranges) {
                if (ingredient in range) {
                    freshIngredients++
                    break
                }
            }
        }
        return freshIngredients
    }

    override fun solvePart2(debug: Boolean): Long {
        var mergedRanges = ranges.toMutableSet()
        var mergeHappened = 0
        while (mergeHappened != mergedRanges.size) {
            mergeHappened = mergedRanges.size
            val toAdd = mutableSetOf<LongRange>()
            for (range in mergedRanges) {
                val merges = mutableSetOf<LongRange>()
                for (mergedRange in mergedRanges) {
                    if (range.last < mergedRange.first || range.first > mergedRange.last) {
                        continue
                    }
                    if (range.last == mergedRange.last && range.first == mergedRange.first)
                        continue
                    val lowerBound = min(mergedRange.first, range.first)
                    val upperBound = max(mergedRange.last, range.last)
                    merges.add(lowerBound..upperBound)
                }
                if (merges.isEmpty())
                    toAdd.add(range)
                toAdd.addAll(merges)
            }
            mergedRanges = toAdd
        }
        val result = mergedRanges.sumOf {
            it.last - it.first + 1
        }
        return result
    }


    override fun parse(input: String) {
        ingredientDatabase = input
        val split = input.split("(\n\n|\r\n\r\n)".toRegex())
        ranges = ArrayList(split[0].lines().map { line ->
            val range = line.split("-")
            range[0].toLong()..range[1].toLong()
        })
        ingredients = split[1].lines().map { line -> line.toLong() }
    }
}
