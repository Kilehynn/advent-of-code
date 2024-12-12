package days

import Day

data class Antenna(val x: Int, val y: Int, val symbol: Char) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Antenna

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

val regexAntenna = Regex("[A-Za-z0-9]")

class Day8 : Day {
    private val antennas: MutableMap<Char, MutableList<Antenna>> = mutableMapOf()
    private val antennasVector: MutableMap<Char, MutableList<Pair<Antenna, Antenna>>> = mutableMapOf()
    private var nbCols = 0
    private var nbRows = 0

    init {
        nbCols = getInput().indexOf(System.lineSeparator())
        val input = getInput().replace(System.lineSeparator(), "")
        nbRows = input.length / nbCols
        val antennaSymbol = regexAntenna.findAll(input).map { it.value[0] }.distinct().toList()
        for (i in input.indices) {
            if (input[i] in antennaSymbol) {
                val antennasArray = antennas.getOrDefault(input[i], mutableListOf())
                antennasArray.add(Antenna(i % nbCols, i / nbCols, input[i]))
                antennas[input[i]] = antennasArray
            }
        }
        antennas.forEach() { (symbol, antennas) ->
            antennas.indices.forEach { antenna1Index ->
                antennas.indices.minus(antenna1Index).forEach() { antenna2Index ->
                    val antenna1 = antennas[antenna1Index]
                    val antenna2 = antennas[antenna2Index]
                    val vector = Pair(antenna1, antenna2)
                    val vectors = antennasVector.getOrDefault(symbol, mutableListOf())
                    vectors.add(vector)
                    antennasVector[symbol] = vectors
                }
            }
        }
    }

    override fun solvePart1(debug: Boolean): Int {
        val antinodePosition: MutableSet<Pair<Int, Int>> = mutableSetOf()
        antennasVector.forEach { (symbol, vectors) ->
            if (debug) {
                println("====================================")
                println("Antennas with frequencies $symbol")
                println()
                println()
            }
            vectors.forEach { (antenna1, antenna2) ->
                if (debug) {
                    println("Antenna 1 at ${antenna1.x},${antenna1.y}")
                    println("Antenna 2 at ${antenna2.x},${antenna2.y}")
                    println("Antinodes between $antenna1 and $antenna2")
                }
                var antiNode =
                    Pair(antenna1.x + 2 * (antenna2.x - antenna1.x), antenna1.y + 2 * (antenna2.y - antenna1.y))
                if (debug)
                    println("Antinode at $antiNode")
                if (!isAntinodeOutside(antiNode)) {
                    antinodePosition.add(antiNode)
                }
                antiNode =
                    Pair(antenna1.x - 1 * (antenna2.x - antenna1.x), antenna1.y - 1 * (antenna2.y - antenna1.y))

                if (!isAntinodeOutside(antiNode)) {
                    antinodePosition.add(antiNode)
                }
                if (debug) {
                    println("Antinode at $antiNode")
                    println()
                }
            }
            if (debug) {
                println("====================================")
                println()
            }
        }

        return antinodePosition.size
    }

    override fun solvePart2(debug: Boolean): Int {
        val antinodePosition: MutableSet<Pair<Int, Int>> = mutableSetOf()
        antennasVector.forEach { (symbol, vectors) ->
            if (debug) {
                println("====================================")
                println("Antennas with frequencies $symbol")
                println()
                println()
            }
            vectors.forEach { (antenna1, antenna2) ->
                if (debug) {
                    println("Antenna 1 at ${antenna1.x},${antenna1.y}")
                    println("Antenna 2 at ${antenna2.x},${antenna2.y}")
                    println("Antinodes between $antenna1 and $antenna2")
                }
                var oppositeDirectionCoeff = -1
                var vectorDirectionCoeff = 1
                while (true) {
                    val vectorDirectionAntinode =
                        Pair(
                            antenna1.x + vectorDirectionCoeff * (antenna2.x - antenna1.x),
                            antenna1.y + vectorDirectionCoeff * (antenna2.y - antenna1.y)
                        )
                    val oppositeDirectionAntinode =
                        Pair(
                            antenna1.x + oppositeDirectionCoeff * (antenna2.x - antenna1.x),
                            antenna1.y + oppositeDirectionCoeff * (antenna2.y - antenna1.y)
                        )
                    if (isAntinodeOutside(vectorDirectionAntinode) && isAntinodeOutside(oppositeDirectionAntinode)) {
                        break
                    }
                    if (!isAntinodeOutside(vectorDirectionAntinode)) {
                        if (!isAntinodeASolitaryAntenna(vectorDirectionAntinode)) {
                            antinodePosition.add(vectorDirectionAntinode)
                        }
                        vectorDirectionCoeff += 1
                    }
                    if (!isAntinodeOutside(oppositeDirectionAntinode)) {
                        if (!isAntinodeASolitaryAntenna(oppositeDirectionAntinode)) {
                            antinodePosition.add(oppositeDirectionAntinode)
                        }
                        oppositeDirectionCoeff -= 1
                    }
                    if (debug) {
                        println("Antinode at $vectorDirectionAntinode")
                        println("Antinode at $oppositeDirectionAntinode")
                        println()
                    }
                }
            }
            if (debug) {
                println("====================================")
                println()
            }
        }
        if (debug)
            renderMap(antinodePosition)
        return antinodePosition.size
    }

    private fun isAntinodeOutside(antinode: Pair<Int, Int>): Boolean {
        return antinode.first !in 0 until nbCols || antinode.second !in 0 until nbRows
    }

    private fun isAntinodeASolitaryAntenna(antinode: Pair<Int, Int>): Boolean {
        return antennas.filter { (_, antennas) -> antennas.size == 1 }.filter { (_, antennas) ->
            antennas[0].x == antinode.first && antennas[0].y == antinode.second
        }.isNotEmpty()
    }

    private fun renderMap(antinodePosition: MutableSet<Pair<Int, Int>>) {
        for (i in 0 until nbCols * nbRows) {
            val x = i % nbCols
            val y = Math.floorDiv(i, nbCols)
            val antenna = antennas.values.flatten().find { it.x == x && it.y == y }
            if (antenna != null && antinodePosition.contains(Pair(x, y))) {
                print("\u001B[31m${antenna.symbol}\u001B[0m")
            } else if (antenna != null) {
                print(antenna.symbol)
            } else if (antinodePosition.contains(Pair(x, y))) {
                print("#")
            } else {
                print(".")
            }
            if (x == nbCols - 1) {
                println()
            }
        }
    }
}