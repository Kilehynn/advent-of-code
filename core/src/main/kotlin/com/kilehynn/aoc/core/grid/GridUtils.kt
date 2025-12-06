package com.kilehynn.aoc.core.grid

fun indexOf(x: Int, y: Int, width: Int): Int =
    y * width + x

fun Position.toIndex(width: Int): Int =
    y * width + x

fun Int.toPosition(width: Int): Position =
    Position(this % width, this / width)

fun Int.move(dir: Direction, width: Int): Int {
    val x = this % width
    val y = this / width
    return (y + dir.y) * width + (x + dir.x)
}
