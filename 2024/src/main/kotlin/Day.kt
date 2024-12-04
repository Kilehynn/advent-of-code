interface Day {
    fun solvePart1(input: String): Int
    fun solvePart2(input: String): Int

    fun getDay(): Int {
        return this::class.java.simpleName.substring(3).toInt()
    }

    fun getInput(): String {
        return this::class.java.getResource("/2024/inputs/day${this.getDay()}.txt")?.readText() ?:""
    }
}