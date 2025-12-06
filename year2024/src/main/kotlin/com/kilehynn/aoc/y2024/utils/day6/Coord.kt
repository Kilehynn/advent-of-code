package com.kilehynn.aoc.y2024.utils.day6

import com.kilehynn.aoc.core.grid.Direction

class Coord(var x: Int, var y: Int, var direction: Direction) {
    fun move(): Coord {
        x += direction.x
        y += direction.y
        return this
    }
    fun copy(): Coord {
        return Coord(x, y, direction)
    }
    fun rotate() {
        direction = direction.rotateClockwise()
    }

    fun getPosition(nbCols: Int): Int {
        return y * nbCols + x
    }

    fun getOrientation(): Char {
        return direction.asChar()
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
