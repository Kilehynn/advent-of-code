package days

import Day


class Day5 : Day {

    private val input: String = getInput()
    private val mapOfDependency: HashMap<Int, MutableList<Int>> = HashMap()
    private val rules = ArrayList<Pair<Int,Int>>()
    private val updates: MutableList<List<Int>> = ArrayList()
    init{
        val sections =
            input.split(System.lineSeparator().repeat(2).toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        sections[0].split(System.lineSeparator()).map { rule -> rule.split("|") }
            .forEach { rule ->
                val key: Int = rule[1].toInt()
                mapOfDependency.putIfAbsent(key, ArrayList())
                mapOfDependency[key]?.add(rule[0].toInt())
                rules.add(Pair(rule[0].toInt(), key))
            }
        sections[1].split(System.lineSeparator()).forEach { line ->
            updates.add(line.split(",").map(Integer::parseInt).toList())
        }
    }
    override fun solvePart1(debug: Boolean): Long {
      return updates.filter(this::isUpdateValid).map { update-> update[update.size / 2] }.sumOf { it }.toLong()
    }

    override fun solvePart2(debug: Boolean): Long {
        updates.filterNot(this::isUpdateValid)
       return updates.filterNot(this::isUpdateValid).map { update ->
           update.sortedWith { a, b ->
               for (rule in rules) {
                   if (rule.first == a && rule.second == b) return@sortedWith -1
                   if (rule.first == b && rule.second == a) return@sortedWith 1
               }
               return@sortedWith 0
           }
       }.sumOf { update -> update[update.size / 2] }.toLong()
    }

    fun isUpdateValid(update: List<Int>): Boolean {
        val pageUpdated = HashMap<Int, Boolean>()
        update.forEach{ page -> pageUpdated.putIfAbsent(page, false) }
        return update.stream().allMatch { page: Int ->
            val dependencies: MutableList<Int>? = mapOfDependency[page]
            pageUpdated[page] = true
            if (dependencies.isNullOrEmpty()) return@allMatch true
            dependencies.stream().allMatch { dependency: Int -> pageUpdated[dependency] == null || pageUpdated.getOrDefault(dependency,true) }
        }
    }
}