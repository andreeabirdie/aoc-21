fun main() {
    fun part1(input: List<String>): Int {
        val oceanFloor = Array(1000) { Array(1000) { 0 } }

        input.forEach { line ->
            var x1 = 0
            var x2 = 0
            var y1 = 0
            var y2 = 0
            line.split(" -> ").apply {
                this[0].split(",").apply {
                    x1 = this[0].toInt()
                    y1 = this[1].toInt()
                }
                this[1].split(",").apply {
                    x2 = this[0].toInt()
                    y2 = this[1].toInt()
                }
            }
            if (x1 == x2) {
                if (y1 > y2) y1 = y2.also { y2 = y1 }
                for (y in y1..y2)
                    oceanFloor[y][x1]++
            } else if (y1 == y2) {
                if (x1 > x2) x1 = x2.also { x2 = x1 }
                for (x in x1..x2)
                    oceanFloor[y1][x]++
            }
        }

        var pointsWithMultipleOverlappingLines = 0
        oceanFloor.forEach { line ->
            line.forEach { if (it > 1) pointsWithMultipleOverlappingLines++ }
        }
        return pointsWithMultipleOverlappingLines
    }

    fun part2(input: List<String>): Int {
        val oceanFloor = Array(1000) { Array(1000) { 0 } }

        input.forEach { line ->
            var x1 = 0
            var x2 = 0
            var y1 = 0
            var y2 = 0
            line.split(" -> ").apply {
                this[0].split(",").apply {
                    x1 = this[0].toInt()
                    y1 = this[1].toInt()
                }
                this[1].split(",").apply {
                    x2 = this[0].toInt()
                    y2 = this[1].toInt()
                }
            }
            if (x1 == x2) {
                if (y1 > y2) y1 = y2.also { y2 = y1 }
                for (y in y1..y2)
                    oceanFloor[y][x1]++
            } else if (y1 == y2) {
                if (x1 > x2) x1 = x2.also { x2 = x1 }
                for (x in x1..x2)
                    oceanFloor[y1][x]++
            }
            else {
                val stepy = if (y1 > y2) -1 else 1
                val stepx = if (x1 > x2) -1 else 1
                while (y1 != y2 && x1 != x2) {
                    oceanFloor[y1][x1]++
                    y1+=stepy
                    x1+=stepx
                }
                oceanFloor[y1][x1]++
            }
        }

        var pointsWithMultipleOverlappingLines = 0
        oceanFloor.forEach { line ->
            line.forEach { if (it > 1) pointsWithMultipleOverlappingLines++ }
        }
        return pointsWithMultipleOverlappingLines
    }

    val input = readInput("Day05")

    println(part1(input))
    println(part2(input))
}
