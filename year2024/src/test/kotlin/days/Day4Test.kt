package com.kilehynn.aoc.y2024.days

import com.kilehynn.aoc.core.Day
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day4Test {
    lateinit var day : Day

    @BeforeEach
    fun beforeTest (){
        day = Day4()
        day.parseInput()
    }

    @Test
    fun solvePart1() {
        assertEquals(18, day.solvePart1())
    }

    @Test
    fun solvePart2() {
        assertEquals(9, day.solvePart2())
    }
}
