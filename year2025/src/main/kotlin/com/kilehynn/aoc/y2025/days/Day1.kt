package com.kilehynn.aoc.y2025.days

import com.kilehynn.aoc.core.BaseDay
import com.kilehynn.aoc.core.grid.Direction

class Day1 : BaseDay() {

    class Instruction(val dir: Direction, val x: Int)

    var instructions = listOf<Instruction>()

    override var expectedPart1: Long? =1059L
    override var expectedPart2: Long? =6305L

    override fun parse(input: String) {
        instructions = lines
            .map { instructionLine ->
                Pair(Direction.fromString(instructionLine.take(1)), instructionLine.substring(1))
            }.map { (dir, instruction) ->
                Instruction(dir, instruction.toInt())
            }
    }

    override fun solvePart1(debug: Boolean): Long {
        var dial = 50
        var nbTimesEqualsZero: Long = 0
        instructions.forEach {
            when (it.dir) {
                Direction.RIGHT -> dial += it.x
                Direction.LEFT -> dial -= it.x
                else -> {}
            }
            if (dial > 99) {
                dial %= 100
            } else if (dial < 0) {
                dial = (100+dial%100)%100
            }
            if (dial == 0)
                nbTimesEqualsZero++
        }
        return nbTimesEqualsZero
    }

    override fun solvePart2(debug: Boolean): Long {
        var dial = 50
        var nbTimesEqualsZero: Long = 0
        instructions.forEach {
            when (it.dir) {
                Direction.RIGHT -> for( i in 1..it.x) {
                    dial ++
                    if (dial == 100) {
                        dial = 0
                    }
                    if (dial == 0)
                        nbTimesEqualsZero++
                }
                Direction.LEFT -> for( i in 1..it.x) {
                    dial--
                    if (dial == -1) {
                        dial = 99
                    }
                    if (dial == 0)
                        nbTimesEqualsZero++
                }

                else -> {}
            }
        }
        return nbTimesEqualsZero
    }

}
