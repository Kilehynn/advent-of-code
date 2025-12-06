package com.kilehynn.aoc.y2025.days

import com.kilehynn.aoc.core.Day
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Day2Test {
    lateinit var day : Day

    @BeforeEach
    fun beforeTest (){
        day = Day2()
        day.parseInput()
    }

    @Test
    fun solvePart1() {
        assertEquals(1227775554,day.solvePart1())
    }

    @Test
    fun solvePart2() {
        assertEquals(4174379265,day.solvePart2())
    }

}
