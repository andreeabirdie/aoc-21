fun main() {
    fun simulateDaysInefficient(input: List<String>, numberOfDays: Int): Int {
        val lanternFishPopulation = mutableListOf<Int>()
        input[0].split(",").forEach { fish -> lanternFishPopulation.add(fish.toInt()) }

        for (i in 0 until numberOfDays) {
            var newFish = 0
            lanternFishPopulation.forEachIndexed { index, fish ->
                if (fish == 0) {
                    newFish++
                    lanternFishPopulation[index] = 6
                } else lanternFishPopulation[index]--
            }
            for (j in 0 until newFish) lanternFishPopulation.add(8)
        }

        return lanternFishPopulation.size
    }

    fun simulateDaysEfficient(input: List<String>, numberOfDays: Int): Long {
        var lanternFish : MutableMap<Int, Long> = input[0].split(",").map { it.toInt() }.groupingBy { it }.eachCount().mapValues { it.value.toLong() }.toMutableMap()
        for (i in 0..8)
            lanternFish.putIfAbsent(i, 0)

        for (day in 0 until numberOfDays) {
            val nrOfParentFish = lanternFish[0]!!

            lanternFish[0] = lanternFish[1]!!
            lanternFish[1] = lanternFish[2]!!
            lanternFish[2] = lanternFish[3]!!
            lanternFish[3] = lanternFish[4]!!
            lanternFish[4] = lanternFish[5]!!
            lanternFish[5] = lanternFish[6]!!
            lanternFish[6] = lanternFish[7]!! + nrOfParentFish
            lanternFish[7] = lanternFish[8]!!
            lanternFish[8] = nrOfParentFish

            if (day % 10 == 0) println(lanternFish.values.sum())
        }

        return lanternFish.values.sum()
    }

    val input = readInput("Day06")

//    println(simulateDaysInefficient(input, 80)) //part1
//    println(simulateDaysEfficient(input, 80)) //part1
    println(simulateDaysEfficient(input, 256)) //part1
}
