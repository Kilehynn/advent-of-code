package com.kilehynn.aoc.y2025.days

import com.kilehynn.aoc.core.Day
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day3Test {
    lateinit var day : Day

    @BeforeEach
    fun beforeTest (){
        day = Day3()
        day.parseInput()
    }

    @Test
    fun solvePart1() {
        assertEquals(357,day.solvePart1())
    }

    @Test
    fun solvePart2() {
        assertEquals(3121910778619,day.solvePart2())
    }

}
