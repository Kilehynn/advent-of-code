package com.kilehynn.aoc.core.grid

class Grid<T>(
val width: Int,
val height: Int,
private val data: MutableList<T>
) {

    init {
        require(data.size == width * height) {
            "Expected data size ${width * height}, got ${data.size}"
        }
    }

    // --- Conversions 1D <-> 2D ---

    fun indexOf(x: Int, y: Int): Int = y * width + x

    fun indexOf(pos: Position): Int = indexOf(pos.x, pos.y)

    fun positionOf(index: Int): Position =
        Position(index % width, index / width)

    // --- Bounds checking ---

    fun isInside(x: Int, y: Int): Boolean =
        x in 0 until width && y in 0 until height

    fun isInside(pos: Position): Boolean = isInside(pos.x, pos.y)

    // --- Accès avec bounds checking ---

    operator fun get(x: Int, y: Int): T {
        require(isInside(x, y)) { "Out of bounds: ($x,$y) in ${width}x$height" }
        return data[indexOf(x, y)]
    }

    operator fun set(x: Int, y: Int, value: T) {
        require(isInside(x, y)) { "Out of bounds: ($x,$y) in ${width}x$height" }
        data[indexOf(x, y)] = value
    }

    operator fun get(pos: Position): T = get(pos.x, pos.y)

    operator fun set(pos: Position, value: T) {
        set(pos.x, pos.y, value)
    }

    // --- Voisins ---

    fun neighbors4(pos: Position): List<Position> {
        val (x, y) = pos
        val candidates = listOf(
            Position(x, y - 1), // UP
            Position(x + 1, y), // RIGHT
            Position(x, y + 1), // DOWN
            Position(x - 1, y), // LEFT
        )
        return candidates.filter { isInside(it) }
    }

    fun neighbors8(pos: Position): List<Position> {
        val (x, y) = pos
        val candidates = listOf(
            Position(x, y - 1),     // UP
            Position(x + 1, y - 1), // TOP_RIGHT
            Position(x + 1, y),     // RIGHT
            Position(x + 1, y + 1), // BOTTOM_RIGHT
            Position(x, y + 1),     // DOWN
            Position(x - 1, y + 1), // BOTTOM_LEFT
            Position(x - 1, y),     // LEFT
            Position(x - 1, y - 1), // TOP_LEFT
        )
        return candidates.filter { isInside(it) }
    }
}
