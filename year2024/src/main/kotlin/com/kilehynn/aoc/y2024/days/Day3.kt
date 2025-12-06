package com.kilehynn.aoc.y2024.days

import com.kilehynn.aoc.core.BaseDay


class Day3 : BaseDay() {
    private val mulRegex = Regex("mul\\((?<leftOperand>\\d{1,3}),(?<rightOperand>\\d{1,3})\\)")
    private val doToDontRegex = """mul\((?<leftOperand>\d{1,3}),(?<rightOperand>\d{1,3})\)|do(n't)?\(\)""".toRegex()

    override var expectedPart1 :Long? = 173785482L
    override var expectedPart2 :Long? = 83158140L

    override fun solvePart1(debug: Boolean): Long {
       return  mulRegex.findAll(input).map { it.groups }.map {
           it["leftOperand"]!!.value.toLong() * it["rightOperand"]!!.value.toLong()
       }.sumOf { it }
    }
    override fun solvePart2(debug: Boolean): Long {
       return doToDontRegex.findAll(input).map{it.groups}.fold(
            Pair(true,0.toLong()), {acc, group ->
                when (group[0]?.value){
                    "do()" ->  Pair(true, acc.second)
                    "don't()" -> Pair(false, acc.second)
                    else -> {
                        if (acc.first) {
                            Pair(true, acc.second + group["leftOperand"]!!.value.toLong() * group["rightOperand"]!!.value.toLong())
                        } else {
                            acc
                        }
                    }
                }
            }
        ).second
    }

    override fun parse(input: String) {
        this.input = input
    }
}
