package com.kilehynn.aoc.app


import com.kilehynn.aoc.core.BaseDay
import com.kilehynn.aoc.core.loadDaysForYear


private val years = listOf(2024,2025)

val allDays: List<BaseDay> =
    years
        .flatMap { loadDaysForYear(it) }
        .sortedWith(compareBy({ it.year }, { it.day }))

fun main(args: Array<String>) {
    when {
        args.isNotEmpty() && args[0] == "-all" -> {
            val years = allDays.map { it.year }.distinct().sorted()
            years.forEachIndexed { idx, year ->
                val daysForYear = allDays
                    .filter { it.year == year }
                    .sortedBy { it.day }

                println("==== YEAR $year ====")
                daysForYear.forEach {
                    println("-".repeat(50))
                    it.printAndSolve()
                }

                if (idx < years.size - 1) {
                    println("=".repeat(70))
                }
            }
        }

        args.size == 2 && args[0] == "-year" -> {
            val year = args[1].toIntOrNull()
                ?: error("Année invalide : ${args[1]}")

            val daysForYear = allDays
                .filter { it.year == year }
                .sortedBy { it.day }

            if (daysForYear.isEmpty()) {
                println("Aucun Day pour $year")
                return
            }

            println("==== YEAR $year ====")
            daysForYear.forEach {
                println("-".repeat(50))
                it.printAndSolve()
            }
        }

        else -> {
            allDays.maxByOrNull { it.year * 100 + it.day }?.printAndSolve()
        }
    }
}
