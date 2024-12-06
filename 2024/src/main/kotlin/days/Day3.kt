package days

import Day

class Day3 : Day {
    private val input = getInput()
    private val mulRegex = Regex("mul\\((?<leftOperand>\\d{1,3}),(?<rightOperand>\\d{1,3})\\)")
    private val doToDontRegex = """mul\((?<leftOperand>\d{1,3}),(?<rightOperand>\d{1,3})\)|do(n't)?\(\)""".toRegex()

    override fun solvePart1(): Int {
       return  mulRegex.findAll(input).map { it.groups }.map {
           (it["leftOperand"]!!.value.toInt() ?: 1) * it["rightOperand"]!!.value.toInt()
       }.sumOf { it }
    }
    override fun solvePart2(): Int {
       return doToDontRegex.findAll(input).map{it.groups}.fold(
            Pair(true,0), {acc, group ->
                when (group[0]?.value){
                    "do()" ->  Pair(true, acc.second)
                    "don't()" -> Pair(false, acc.second)
                    else -> {
                        if (acc.first) {
                            Pair(acc.first, acc.second + group["leftOperand"]!!.value.toInt() * group["rightOperand"]!!.value.toInt())
                        } else {
                            acc
                        }
                    }
                }
            }
        ).second
    }
}