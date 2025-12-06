package com.kilehynn.aoc.core.grid

data class Coordinates(
    var pos: Position,
    var direction: Direction
) {

    fun move() {
        pos = pos.move(direction)
    }

    fun move(direction: Direction) {
        this.direction = direction
        pos = pos.move(direction)
    }


    fun rotateClockwise() {
        direction = direction.rotateClockwise()
    }

    fun rotateCounterClockwise() {
        direction = direction.rotateCounterClockwise()
    }

    fun toIndex(width: Int): Int =
        pos.toIndex(width)

    fun orientationChar(): Char =
        direction.asChar()

    fun copyCoord(): Coordinates =
        Coordinates(pos, direction)
}
