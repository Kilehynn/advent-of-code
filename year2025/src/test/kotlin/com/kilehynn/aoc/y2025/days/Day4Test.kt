package com.kilehynn.aoc.y2025.days

import com.kilehynn.aoc.core.Day
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day4Test {
    lateinit var day : Day

    @BeforeEach
    fun beforeTest (){
        day = Day4()
        day.parseInput()
    }

    @Test
    fun solvePart1() {
        assertEquals(13,day.solvePart1(true))
    }

    @Test
    fun solvePart2() {
        assertEquals(43,day.solvePart2(true))
    }

}
