package days

import Day
import java.math.BigInteger

class Day9 : Day {
    private val decompressedFS: MutableList<Int> = mutableListOf()

    init {
        var byteValue = 0
        getInput().forEachIndexed { index, c ->
            if (index and 1 == 0) {
                repeat(c.toString().toInt()) {
                    decompressedFS.add(byteValue)
                }
                byteValue++
            } else {
                repeat(c.toString().toInt()) {
                    decompressedFS.add(-1)
                }
            }

        }
    }

    override fun solvePart1(debug: Boolean): Long {
        if (debug) {
            printFileSystem()
        }
        var i = 0
        var j = decompressedFS.size - 1
        while (i < j) {
            if (decompressedFS[i] == -1) {
                decompressedFS[i] = decompressedFS[j]
                decompressedFS[j] = -1
                i++
                while (decompressedFS[j] == -1) {
                    j--
                }

            } else {
                i++
            }
        }
        if (debug) {
            printFileSystem()
        }
       return decompressedFS.filter { c -> c != -1 }.foldIndexed(0) { index, acc, c ->
            acc + index * c
        }
    }

    private fun printFileSystem() {
        decompressedFS.forEach {
            if (it == -1) {
                print(".")
            } else {
                print(it)
                print("|")
            }
        }
        println()
    }

    override fun solvePart2(debug: Boolean): Long {
        return 0
    }
}