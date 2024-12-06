interface Day {
    fun solvePart1(): Int
    fun solvePart2(): Int

    fun getDay(): Int {
        return this::class.java.simpleName.substring(3).toInt()
    }

    fun getInput(): String {
        return this::class.java.getResource("/2024/inputs/day${this.getDay()}.txt")?.readText() ?:""
    }
}