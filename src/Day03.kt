class ByteNumber(private val number: String) {
    val bytes = MutableList(12) { 0 }

    init {
        val nr = number.split("")
        for (i in 1 until nr.size - 1) {
            bytes[i - 1] = nr[i].toInt()
        }
    }

    public fun toInt(): Int {
        var numberStr = ""
        bytes.forEach {
            numberStr += it.toString()
        }
        return Integer.parseInt(numberStr, 2)
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val ones = MutableList(12) { 0 }

        input.forEach {
            val nr = it.split("")
            for (i in 1 until nr.size - 1) {
                if (nr[i].toInt() == 1) ones[i - 1]++
            }
        }

        var gammaStr = ""
        var epsilonStr = ""
        ones.forEach {
            if (it < input.size - it) {
                gammaStr += "1"
                epsilonStr += "0"
            } else {
                gammaStr += "0"
                epsilonStr += "1"
            }
        }
        val gamma = Integer.parseInt(gammaStr, 2)
        val epsilon = Integer.parseInt(epsilonStr, 2)

        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        var oxygenNumbers = mutableListOf<ByteNumber>()
        var co2Numbers = mutableListOf<ByteNumber>()

        input.forEach {
            oxygenNumbers.add(ByteNumber(it))
            co2Numbers.add(ByteNumber(it))
        }

        var index = 0
        while (oxygenNumbers.size > 1) {
            var nrOfOnesAtPositionIndex = 0
            oxygenNumbers.forEach {
                if (it.bytes[index] == 1) nrOfOnesAtPositionIndex++
            }
            oxygenNumbers = if (nrOfOnesAtPositionIndex >= oxygenNumbers.size - nrOfOnesAtPositionIndex) {
                oxygenNumbers.filter { it.bytes[index] == 1 }.toMutableList()
            } else oxygenNumbers.filter { it.bytes[index] == 0 }.toMutableList()
            index++
        }

        val oxygenGenerator = oxygenNumbers[0].toInt()

        index = 0
        while (co2Numbers.size > 1) {
            var nrOfOnesAtPositionIndex = 0
            co2Numbers.forEach {
                if (it.bytes[index] == 1) nrOfOnesAtPositionIndex++
            }
            co2Numbers = if (nrOfOnesAtPositionIndex < co2Numbers.size - nrOfOnesAtPositionIndex) {
                co2Numbers.filter { it.bytes[index] == 1 }.toMutableList()
            } else co2Numbers.filter { it.bytes[index] == 0 }.toMutableList()
            index++
        }

        val co2Rating = co2Numbers[0].toInt()
        return oxygenGenerator * co2Rating
    }


    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
