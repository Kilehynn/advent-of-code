package com.kilehynn.aoc.y2025.days

import com.kilehynn.aoc.core.BaseDay
import com.kilehynn.aoc.core.grid.Direction
import com.kilehynn.aoc.core.grid.Grid
import com.kilehynn.aoc.core.grid.Position

class Day4 : BaseDay() {

    override var expectedPart1: Long? = 1553L
    override var expectedPart2: Long? = 8442L

    var matrix: Grid<Char> = Grid(0, 0, mutableListOf())

    override fun solvePart1(debug: Boolean): Long {
        var pos = Position(0, 0)
        var numberOfAccessiblePaper = 0L
        while (matrix.isInside(pos)) {
            if (matrix[pos] == '@') {
                val numberOfSurroundingPaper = matrix.neighbors8(pos).sumOf { if (matrix[it] == '@') 1 else 0 }
                if (numberOfSurroundingPaper < 4) {
                    numberOfAccessiblePaper++
                    if (debug) {
                        print("x")
                    }
                } else if (debug) {
                    print("@")
                }
            } else if (debug) {
                print(".")
            }
            pos = pos.move(Direction.RIGHT)
            if (pos.x >= matrix.width) {
                if (debug) {
                    println()
                }
                pos = Position(0, pos.y).move(Direction.DOWN)
            }
        }
        return numberOfAccessiblePaper
    }

    override fun solvePart2(debug: Boolean): Long {
        var result = 0L
        var numberOfAccessiblePaper: Long
        do {
            if (debug) {
                println("==========")
            }
            numberOfAccessiblePaper = 0
            var pos = Position(x = 0, y = 0)
            while (matrix.isInside(pos)) {
                if (matrix[pos] == '@') {
                    val numberOfSurroundingPaper = matrix.neighbors8(pos).sumOf { if (matrix[it] == '@') 1 else 0 }
                    if (numberOfSurroundingPaper < 4) {
                        numberOfAccessiblePaper++
                        matrix[pos] = 'x'
                    }
                }
                if (debug) {
                    print(matrix[pos])
                }
                pos = pos.move(Direction.RIGHT)
                if (pos.x >= matrix.width) {
                    if (debug) {
                        println()
                    }
                    pos = Position(0, pos.y).move(Direction.DOWN)
                }
            }
            result += numberOfAccessiblePaper
            if (debug) {
                println("==========")
            }
        } while (numberOfAccessiblePaper > 0)

        return result
    }


    override fun parse(input: String) {
        this.matrix = Grid(lines[0].length, lines.size, input.replace("(\n|\r\n)".toRegex(), "").toMutableList())
    }

}
