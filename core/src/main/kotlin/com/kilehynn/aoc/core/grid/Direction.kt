package com.kilehynn.aoc.core.grid

enum class Direction(val x: Int, val y: Int, vararg val aliases: String) {
    RIGHT(1, 0, "R"),
    LEFT(-1, 0, "L"),
    UP(0, -1, "U"),
    DOWN(0, 1, "D"),

    TOP_RIGHT(1, -1, "TR"),
    TOP_LEFT(-1, -1, "TL"),
    BOTTOM_RIGHT(1, 1, "BR"),
    BOTTOM_LEFT(-1, 1, "BL");

    fun rotateClockwise(): Direction = when (this) {
        UP          -> RIGHT
        RIGHT       -> DOWN
        DOWN        -> LEFT
        LEFT        -> UP
        TOP_RIGHT,
        TOP_LEFT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT -> RIGHT
    }

    fun rotateCounterClockwise(): Direction = when (this) {
        UP          -> LEFT
        LEFT        -> DOWN
        DOWN        -> RIGHT
        RIGHT       -> UP
        TOP_RIGHT,
        TOP_LEFT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT -> LEFT
    }

    fun asChar(): Char = when (this) {
        UP    -> '^'
        DOWN  -> 'v'
        LEFT  -> '<'
        RIGHT -> '>'
        else  -> '?'
    }

    companion object {
        private val byName = entries.associateBy { it.name }
        private val byAlias = entries
            .flatMap { e -> e.aliases.map { alias -> alias to e } }
            .toMap()

        fun fromString(s: String): Direction =
            byName[s.uppercase()] ?: byAlias[s.uppercase()] ?: error("Unknown direction: '$s'")
    }
}
