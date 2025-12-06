package com.kilehynn.aoc.y2025.days

import com.kilehynn.aoc.core.BaseDay
import java.util.function.BinaryOperator
import java.util.function.LongBinaryOperator

class Day6 : BaseDay() {
    override var expectedPart1: Long? = 5782351442566L
    override var expectedPart2: Long? = 10194584711842L

    enum class Operator(val s: String) : BinaryOperator<Long>, LongBinaryOperator {
        MULTIPLY("*") {
            override fun apply(t: Long, u: Long): Long = t * u
        },
        ADD("+") {
            override fun apply(t: Long, u: Long): Long = t + u
        };

        override fun applyAsLong(left: Long, right: Long): Long = apply(left, right)

        companion object {
            infix fun from(value: String): Operator = entries.first { it.s == value }
        }
    }


    class Problem(var operator: Operator = Operator.MULTIPLY, val operands: MutableList<String> = mutableListOf()) {
        override fun toString(): String {
            return "Problem(${operands.joinToString(operator.s, transform = { it })} = ${solve()})"
        }

        fun solve(): Long {
            return operands.fold(if (operator == Operator.MULTIPLY) 1 else 0) { acc, l ->
                operator.apply(
                    acc,
                    l.toLong()
                )
            }
        }
    }

    var problemSheet = ""

    override fun solvePart1(debug: Boolean): Long {
        val problemList = mutableListOf<Problem>()
        val split =
            problemSheet.replace(" {2,}".toRegex(), " ").split("(\n|\r\n)".toRegex()).map { it.trim().split(" ") }
        for (y in 0 until split[0].size) {
            val problem = Problem()
            for (x in 0 until split.size - 1) {
                problem.operands.add(split[x][y])
            }
            problem.operator = Operator.from(split.last()[y])

            problemList.add(problem)
        }
        if (debug) {
            problemList.forEach { println(it) }
        }
        return problemList.sumOf { problem -> problem.solve() }
    }

    override fun solvePart2(debug: Boolean): Long {
        val problemList = mutableListOf<Problem>()
        val split = problemSheet.split("(\n|\r\n)".toRegex())

        val operandLine =
            split.last().trim().replace(" {2,}".toRegex(), " ").split(" ".toRegex()).map { Operator.from(it) }
        for (i in operandLine.size - 1 downTo 0) {
            problemList.add(Problem(operator = operandLine[i], operands = mutableListOf()))
        }

        var problemIndex = 0
        for (y in 0 until split.size -1) {
            var operandIndex = 0
            for (x in split[y].length - 1 downTo 0) {
                val problem = problemList[problemIndex]
                val currentChar = split[y][x]
                if (currentChar == ' ') {
                    operandIndex++
                    if (isEmptyColumn(x, split)) {
                        operandIndex = 0
                        problemIndex++
                    }
                    continue
                }
                while (problem.operands.size <= operandIndex) {
                    problem.operands.add("")
                }
                problem.operands[operandIndex] += currentChar
                operandIndex++
            }
            problemIndex = 0
        }
        if (debug) {
            problemList.forEach { println(it) }
        }
        return problemList.sumOf { problem -> problem.solve() }
    }

    fun isEmptyColumn(column: Int, list: List<String>): Boolean {
        for (i in 0 until list.size) {
            if (list[i][column] != ' ') {
                return false
            }
        }
        return true
    }


    override fun parse(input: String) {
        problemSheet = input
    }
}
