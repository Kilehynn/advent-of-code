package com.kilehynn.aoc.y2025.days

import com.kilehynn.aoc.core.Day
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {
    lateinit var day : Day

     @BeforeEach
    fun beforeTest (){
        day = Day1()
        day.parseInput()
    }

    @Test
    fun solvePart1() {
        assertEquals(3, day.solvePart1())
    }

    @Test
    fun solvePart2() {
        assertEquals(6, day.solvePart2())
    }

}
