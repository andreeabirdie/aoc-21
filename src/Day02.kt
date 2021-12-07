fun main() {
    fun part1(input: List<String>): Int {
        var x = 0
        var y = 0

        input.forEach { move ->
            move.split(" ").apply {
                val steps = this[1].toInt()
                when (this[0]) {
                    "forward" -> x += steps
                    "up" -> y -= steps
                    "down" -> y += steps
                }
            }
        }
        return x * y
    }

    fun part2(input: List<String>): Int {
        var x = 0
        var y = 0
        var aim = 0

        input.forEach { move ->
            move.split(" ").apply {
                val steps = this[1].toInt()
                when (this[0]) {
                    "forward" -> {
                        x += steps
                        y += aim * steps
                    }
                    "up" -> aim -= steps
                    "down" -> aim += steps
                }
            }
        }
        return x * y

        return 0
    }


    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
