package com.kilehynn.aoc.core.grid


data class Position(val x: Int, val y: Int) {
    fun move(dx: Int, dy: Int): Position =
        Position(x + dx, y + dy)

    operator fun plus(dir: Direction): Position =
        Position(x + dir.x, y + dir.y)

    fun move(dir: Direction): Position =
        Position(x + dir.x, y + dir.y)

    fun neighbors4(): List<Position> =
        listOf(
            this + Direction.UP,
            this + Direction.RIGHT,
            this + Direction.DOWN,
            this + Direction.LEFT,
        )

    fun neighbors8(): List<Position> =
        Direction.entries.map { this + it }
}
