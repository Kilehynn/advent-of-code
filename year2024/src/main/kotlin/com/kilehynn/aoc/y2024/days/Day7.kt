package com.kilehynn.aoc.y2024.days

import com.kilehynn.aoc.core.BaseDay
import kotlin.math.pow


class Day7 :  BaseDay() {
    data class Equation(val result: Long, val operands: List<Long>)

    private val equations: MutableList<Equation> = mutableListOf()

    override var expectedPart1:Long? = 1153997401072L
    override var expectedPart2 :Long? = 97902809384118L

    override fun parse(input: String) {
        this.input = input
        lines = input.lines()
        lines.forEach { line ->
            val equation = line.trim().split(":")
            val result = equation[0].trim().toLong()
            val operands = equation[1].trim().split(" ").map { it.trim().toLong() }
            equations.add(Equation(result, operands))
        }
    }

    private fun solveEquation(
        equation: Equation,
        operators: List<Pair<String, (Long, Long) -> Long>>,
        debug: Boolean = false
    ): Long {
        val numberOfPossibleOperator = equation.operands.size - 1
        for (i in 0 until operators.size.toDouble().pow(numberOfPossibleOperator.toDouble()).toInt()) {
            val operatorCombination = mutableListOf<Pair<String, (Long, Long) -> Long>>()
            for (j in 0 until numberOfPossibleOperator) {
                val operatorIndex = (i / operators.size.toDouble().pow(j.toDouble()).toInt()) % operators.size
                operatorCombination.add(operators[operatorIndex])
            }
            var successfulEquation = ""
            val result = equation.operands.foldIndexed(0.toLong()) { index, acc, operand ->
                if (index == 0) {
                    if (debug)
                        successfulEquation = "$operand"
                    operand
                } else {
                    if (debug)
                        successfulEquation += " ${operatorCombination[index - 1].first} $operand"
                    operatorCombination[index - 1].second(acc, operand)
                }
            }
            if (result == equation.result) {
                if (debug)
                    println("$successfulEquation = ${equation.result}")
                return result
            }
        }
        return 0
    }

    override fun solvePart1(debug: Boolean): Long {
        val operators = listOf(
            "+" to { a: Long, b: Long -> a + b },
            "*" to { a: Long, b: Long -> a * b }
        )
        println("Result part 1 : ${equations.sumOf { solveEquation(it, operators) }}")
        return  equations.sumOf { solveEquation(it, operators) }
    }


    override fun solvePart2(debug: Boolean): Long {
        val operators = listOf(
            "+" to { a: Long, b: Long -> a + b },
            "*" to { a: Long, b: Long -> a * b },
            "||" to { a: Long, b: Long -> ("${a}${b}".toLong()) }
        )
        return equations.sumOf { solveEquation(it, operators) }
    }
}
