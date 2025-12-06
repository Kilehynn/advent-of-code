package com.kilehynn.aoc.core

fun loadDaysForYear(
    year: Int,
    maxDay: Int = 25
): List<BaseDay> {
    val pkg = "com.kilehynn.aoc.y$year.days"

    return (1..maxDay).mapNotNull { day ->
        val className = "$pkg.Day$day"
        try {
            val clazz = Class.forName(className).asSubclass(BaseDay::class.java)
            val ctor = clazz.getDeclaredConstructor()
            ctor.newInstance()
        } catch (_: ClassNotFoundException) {
            null
        } catch (_: NoSuchMethodException) {
            null
        }
    }
}

inline fun <T> measure(block: () -> T): Pair<T, Long> {
    val start = System.currentTimeMillis()
    val result = block()
    val end = System.currentTimeMillis()
    return result to (end - start)
}
