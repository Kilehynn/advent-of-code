package utils.day6

import utils.DIRECTION

class Coord(var x: Int, var y: Int, var direction: DIRECTION) {
    fun move(): Coord {
        x += direction.x
        y += direction.y
        return this
    }
    fun copy(): Coord {
        return Coord(x, y, direction)
    }
    fun rotate() {
        direction = when(direction) {
            DIRECTION.UP -> DIRECTION.RIGHT
            DIRECTION.RIGHT -> DIRECTION.DOWN
            DIRECTION.DOWN -> DIRECTION.LEFT
            DIRECTION.LEFT -> DIRECTION.UP
            else -> DIRECTION.RIGHT
        }
    }

    fun getPosition(nbCols: Int): Int {
        return y * nbCols + x
    }

    fun getOrientation(): Char {
        return when(direction) {
            DIRECTION.UP -> '^'
            DIRECTION.DOWN -> 'v'
            DIRECTION.LEFT -> '<'
            DIRECTION.RIGHT -> '>'
            else -> '^'
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Coord

        if (x != other.x) return false
        if (y != other.y) return false
        if (direction != other.direction) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + direction.hashCode()
        return result
    }


}