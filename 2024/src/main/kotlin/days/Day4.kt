package days

import Day
import utils.DIRECTION


class Day4: Day {
    private var input: String
    private var nbCols: Int = 0
    private var nbLines: Int = 0
    private val nextCharMap = mapOf(
        'X' to 'M', 'M' to 'A', 'A' to 'S'
    )

    init{
        val matrix = getInput().split(System.lineSeparator().toRegex())
        nbLines = matrix.size
        nbCols = matrix[0].length
        this.input = getInput().replace(System.lineSeparator().toRegex(), "")
    }
    override fun solvePart1(): Int {
        var x = 0
        var y = 0
        var numberXmas = 0
        while (y * nbCols + x < nbCols * nbLines) {
            val pos = y * nbCols + x
            if (input[pos] == 'X') {
                val currentX = x
                val currentY = y
                numberXmas +=DIRECTION.entries.map { direction: DIRECTION ->
                    findXMAS(currentX, currentY, direction = direction)
                }.sumOf { it }
            }
            x += 1
            if (x >= nbCols) {
                x = 0
                y += 1
            }
        }
        return numberXmas
    }

    override fun solvePart2(): Int {
        var x = 0
        var y = 0
        var numberXmas = 0
        while (y * nbCols + x < nbCols * nbLines) {
            val pos = y * nbCols + x
            if (input[pos] == 'A') {
                if (x > 0 && x + 1 < nbCols && y > 0 && y + 1 < nbLines) {
                    val topLeft = (y - 1) * nbCols + x - 1
                    val topRight = (y - 1) * nbCols + x + 1
                    val bottomLeft = (y + 1) * nbCols + x - 1
                    val bottomRight = (y + 1) * nbCols + x + 1

                    val leftDiag =
                        (input[topLeft] == 'M' && input[bottomRight] == 'S') || (input[topLeft] == 'S' && input[bottomRight] == 'M')
                    val rightDiag =
                        (input[topRight] == 'M' && input[bottomLeft] == 'S') || (input[topRight] == 'S' && input[bottomLeft] == 'M')
                    numberXmas += if (leftDiag && rightDiag) 1 else 0
                }
            }
            x += 1
            if (x >= nbCols) {
                x = 0
                y += 1
            }
        }
        return numberXmas
    }

    private fun findXMAS(x: Int, y: Int, letterToLookFor: Char = 'X', direction: DIRECTION): Int {
        if (((direction === DIRECTION.LEFT || direction === DIRECTION.TOP_LEFT || direction === DIRECTION.BOTTOM_LEFT) && x < 0)
            || ((direction === DIRECTION.RIGHT || direction === DIRECTION.TOP_RIGHT || direction === DIRECTION.BOTTOM_RIGHT) && x >= nbCols)
            || ((direction === DIRECTION.UP || direction === DIRECTION.TOP_RIGHT || direction === DIRECTION.TOP_LEFT) && y < 0)
            || ((direction === DIRECTION.DOWN || direction === DIRECTION.BOTTOM_LEFT || direction === DIRECTION.BOTTOM_RIGHT) && y >= nbLines)
        ) return 0

        val pos = y * nbCols + x
        if (letterToLookFor == 'S' && input[pos] == letterToLookFor) return 1
        if (letterToLookFor != input[pos]) return 0
        return findXMAS(x + direction.x, y + direction.y, nextCharMap[letterToLookFor]!!, direction)
    }
}