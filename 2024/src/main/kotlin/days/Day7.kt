package days

import Day
import java.math.BigInteger
import kotlin.math.pow


data class Equation(val result: Long, val operands: List<Long>)
class Day7 : Day {

    private val equations: MutableList<Equation> = mutableListOf()

    init {
        val input = getInput()
        input.split(System.lineSeparator()).forEach { line ->
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
        return 0;
    }


    override fun solvePart2(debug: Boolean): Long {
        val operators = listOf(
            "+" to { a: Long, b: Long -> a + b },
            "*" to { a: Long, b: Long -> a * b },
            "||" to { a: Long, b: Long -> ("${a}${b}".toLong()) }
        )
        println("Result part 2 : ${equations.sumOf { solveEquation(it, operators) }}")
        return 0;
    }
}