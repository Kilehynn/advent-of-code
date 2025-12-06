package com.kilehynn.aoc.y2024.days


import com.kilehynn.aoc.core.BaseDay
import com.kilehynn.aoc.core.grid.Direction
import com.kilehynn.aoc.core.grid.Grid
import com.kilehynn.aoc.core.grid.Position


class Day4 :  BaseDay() {
    private var grid: Grid<Char> = Grid(0,0, mutableListOf())
    private val nextCharMap = mapOf(
        'X' to 'M', 'M' to 'A', 'A' to 'S'
    )
    override var expectedPart1 :Long? = 2521L
    override var expectedPart2 :Long? = 1912L

    override fun parse(input: String) {
        grid = Grid(lines[0].length, lines.size, lines.flatMap { it.toList() }.toMutableList())
    }

    override fun solvePart1(debug: Boolean): Long {
        var pos = Position(0, 0)
        var numberXmas: Long = 0
        while (grid.isInside(pos)) {
            if (grid[pos] == 'X') {
                numberXmas += Direction.entries.map { direction ->
                    findXMAS(pos.move(direction),'M', direction)
                }.sumOf { it }
            }
            pos = pos.move(Direction.RIGHT)
            if (pos.x >= grid.width) {
                pos = Position(0, pos.y +1)
            }
        }
        return numberXmas
    }

    override fun solvePart2(debug: Boolean): Long {
        var pos = Position(0, 0)
        var numberXmas: Long = 0
        while (grid.isInside(pos)) {
            if (grid[pos] == 'A') {
                val topLeft = Position( pos.x - 1,pos.y - 1)
                val bottomRight = Position( pos.x + 1,pos.y + 1)
                if (grid.isInside(topLeft) && grid.isInside(bottomRight)) {
                    val topRight = Position( pos.x + 1,pos.y - 1)
                    val bottomLeft =Position( pos.x - 1,pos.y + 1)

                    val leftDiag =
                        (grid[topLeft] == 'M' && grid[bottomRight] == 'S') || (grid[topLeft] == 'S' && grid[bottomRight] == 'M')
                    val rightDiag =
                        (grid[topRight] == 'M' && grid[bottomLeft] == 'S') || (grid[topRight] == 'S' && grid[bottomLeft] == 'M')
                    numberXmas += if (leftDiag && rightDiag) 1 else 0
                }
            }
            pos = pos.move(Direction.RIGHT)
            if (pos.x >= grid.width) {
                pos = Position(0, pos.y +1)
            }
        }
        return numberXmas
    }

    private fun findXMAS(pos: Position, letterToLookFor: Char = 'X', direction: Direction): Int {
        if (!grid.isInside(pos) || letterToLookFor != grid[pos])
            return 0

        if (letterToLookFor == 'S') return 1
        return findXMAS(pos.move(direction) , nextCharMap[letterToLookFor]!!, direction)
    }
}
