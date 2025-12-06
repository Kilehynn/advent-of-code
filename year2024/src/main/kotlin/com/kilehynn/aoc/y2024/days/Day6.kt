package com.kilehynn.aoc.y2024.days

import com.kilehynn.aoc.core.BaseDay
import com.kilehynn.aoc.core.grid.Direction
import com.kilehynn.aoc.y2024.utils.day6.Coord
import com.kilehynn.aoc.y2024.utils.day6.Map
import utils.day6.GameResult


class Day6 :  BaseDay() {
    private val characterRepresentation = charArrayOf('v', '^', '<', '>')
    private lateinit var baseMap: Map

    override var expectedPart1:Long? = 5461L
    override var expectedPart2 :Long? = 1836L

    override fun parse(input: String) {
        val matrix = input.split(System.lineSeparator().toRegex())
        val nbLines = matrix.size
        val nbCols = matrix[0].length
        val obstacles = mutableSetOf<Pair<Int, Int>>()
        val baseCoord = Coord(0, 0, Direction.UP)
        input.replace(System.lineSeparator(), "").forEachIndexed { index, c ->
            val x = (index) % nbCols
            val y = Math.floorDiv(index, nbCols)
            if (characterRepresentation.contains(c)) {
                baseCoord.x = x
                baseCoord.y = y
                baseCoord.direction = when (c) {
                    '^' -> Direction.UP
                    'v' -> Direction.DOWN
                    '<' -> Direction.LEFT
                    '>' -> Direction.RIGHT
                    else -> {
                        Direction.UP
                    }
                }
            } else if (c == '#') {
                obstacles.add(Pair(x, y))
            }
        }
        this.baseMap = Map(nbCols, nbLines, baseCoord, obstacles)
    }


    override fun solvePart1(debug: Boolean): Long {
        val part1Map = baseMap.copy()
        play(part1Map)
        return part1Map.previousPositions.size.toLong()
    }

    private fun play(map: Map, stopIfLooping: Boolean = false): GameResult {
        while (map.currentCoord.y in 0..<map.nbLines && map.currentCoord.x in 0..map.nbCols) {
            val result = map.playTurn(stopIfLooping)
            if (result.exited || result.looped) {
                return result
            }
        }
        return GameResult(exited = true, false)
    }


    override fun solvePart2(debug: Boolean): Long {
        var numberOfPotentialObstacles = 0
        val newObstacles = mutableSetOf<Pair<Int, Int>>()

        val map = baseMap.copy()
        while (true) {
            val nextMove = map.currentCoord.copy().move()
            if (!newObstacles.contains(Pair(nextMove.x, nextMove.y))) {
                newObstacles.add(Pair(nextMove.x, nextMove.y))
                val newMap = map.copy()
                newMap.obstacles.add(Pair(nextMove.x, nextMove.y))
                while (true) {
                    val result = newMap.playTurn(true)
                    if (result.looped) {
                        numberOfPotentialObstacles++
                        break
                    }
                    if (result.exited) {
                        break
                    }
                }
            }
            if (map.playTurn(true).exited) {
                break
            }
        }
        return numberOfPotentialObstacles.toLong()
    }

}
