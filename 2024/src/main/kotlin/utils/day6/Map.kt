package utils.day6

class Map(
    val nbCols: Int,
    val nbLines: Int,
    private val baseCoord: Coord,
    val obstacles: MutableSet<Pair<Int, Int>>,
    val previousPositions: MutableSet<Int> = mutableSetOf(baseCoord.getPosition(nbCols)),
    private val previousPositionOriented: MutableSet<Coord> = mutableSetOf(baseCoord.copy()),
    val currentCoord: Coord = baseCoord.copy()
) {

    fun copy(): Map {
        return Map(nbCols, nbLines, baseCoord.copy(), obstacles.toMutableSet(), previousPositions.toMutableSet(), previousPositionOriented.toMutableSet(), currentCoord.copy())
    }

    fun renderMap() {
        val previousPositionsInt = previousPositionOriented.map { it.getPosition(nbCols) }.toSet()
        for (i in 0 until nbCols * nbLines) {
            val isPreviousPosition = previousPositionsInt.contains(i)
            if (i == currentCoord.getPosition(nbCols)) {
                print(currentCoord.getOrientation())
            } else if (isPreviousPosition) {
                print("o")
            } else if (obstacles.contains(Pair(i % nbCols, Math.floorDiv(i, nbCols)))) {
                print("#")
            } else {
                print(".")
            }
            if (i % nbCols == nbCols - 1) {
                println()
            }
        }
    }

    fun playTurn(stopIfLooping: Boolean = false): GameResult {
        val nextCoord = currentCoord.copy().move()
        if (nextCoord.y !in 0 until nbLines || nextCoord.x !in 0 until nbCols) {
            return GameResult(exited = true, looped = false)
        }
        if (obstacles.contains(Pair(nextCoord.x, nextCoord.y))) {
            currentCoord.rotate()
        }
        currentCoord.move()

        if (stopIfLooping && previousPositionOriented.contains(currentCoord)) {
            return GameResult(exited = false, true)
        }
        previousPositionOriented.add(currentCoord.copy())
        previousPositions.add(currentCoord.getPosition(nbCols))
        return GameResult(exited = false, looped = false)
    }

}