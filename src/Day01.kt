fun main() {
    fun part1(input: List<String>): Int {
        var lastDepth: Int? = null
        var increaseCounter = 0

        input.forEach {
            it.toInt().let { currentDepth ->
                lastDepth?.let { if (it < currentDepth) increaseCounter++ }
                lastDepth = currentDepth
            }
        }

        return increaseCounter
    }

    fun part2(input: List<String>): Int {
        //current and last slides have 2 values in common, it's enough to compare i and i+3 values
        var increaseCounter = 0

        for (i in 3 until input.size) {
            if (input[i].toInt() > input[i - 3].toInt()) increaseCounter++
        }

        return increaseCounter
    }


    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
