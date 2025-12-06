package com.kilehynn.aoc.y2025.days

import com.kilehynn.aoc.core.Day
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day6Test {
    lateinit var day : Day

    @BeforeEach
    fun beforeTest (){
        day = Day6()
        day.parseInput()
    }

    @Test
    fun solvePart1() {
        assertEquals(4277556, day.solvePart1())
    }

    @Test
    fun solvePart2() {
        assertEquals(3263827, day.solvePart2())
    }

}
