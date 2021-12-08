import java.lang.Exception

class BoardNumber(var number: Int) {
    var marked: Boolean = false
}

class Board(private val numbers: List<String>) {
    val values = List(5) {
        List(5) { BoardNumber(0) }
    }

    init {
        for (i in 0..4)
            for (j in 0..4) {
                values[i][j].number = numbers[i * 5 + j].toInt()
            }
    }

    fun markNumber(calledNumber: Int): Int {
        for (i in 0..4)
            for (j in 0..4) {
                if (values[i][j].number == calledNumber) {
                    values[i][j].marked = true
                    return checkEndGame(i, j, calledNumber)
                    break
                }
            }
        return -1
    }

    private fun checkEndGame(rowToCheck: Int, columnToCheck: Int, calledNumber: Int): Int {
        var markedOnRow = 0
        var markedOnColumn = 0
        for (i in 0..4) {
            if (values[rowToCheck][i].marked) markedOnRow++
            if (values[i][columnToCheck].marked) markedOnColumn++
        }
        if (markedOnRow == 5 || markedOnColumn == 5) return getScore(calledNumber)
        return -1
    }

    fun getScore(calledNumber: Int): Int {
        var score = 0
        for (i in 0..4)
            for (j in 0..4)
                if (!values[i][j].marked) score += values[i][j].number
        return score * calledNumber
    }
}

fun main() {
    fun part1(boards: MutableList<Board>, bingoNumbers: List<String>) {
        try {
            bingoNumbers.forEach { calledNumber ->
                boards.forEach { board ->
                    val bingo = board.markNumber(calledNumber.toInt())
                    if (bingo != -1) {
                        throw Exception(bingo.toString())
                    }
                }
            }
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun part2(boards: MutableList<Board>, bingoNumbers: List<String>): Int {
        bingoNumbers.forEach { calledNumber ->
            var i = 0
            while (i < boards.size) {
                val bingo = boards[i].markNumber(calledNumber.toInt())
                if (bingo != -1 && boards.size > 1) {
                    println("Removed board $i after marking $calledNumber, Score: $bingo. Boards still in game: ${boards.size}")
                    boards.remove(boards[i])
                    i--
                } else if (boards.size == 1 && bingo != -1) return boards[i].getScore(calledNumber.toInt())
                i++
            }
        }
        return boards[0].getScore(bingoNumbers.last().toInt())
    }

    val input = readInput("Day04")
    val boards = mutableListOf<Board>()
    val bingoNumbers = input[0].split(",")

    for (i in 2..input.size step (6)) {
        boards.add(
            Board(
                (input[i].split(" ")
                        + input[i + 1].split(" ")
                        + input[i + 2].split(" ")
                        + input[i + 3].split(" ")
                        + input[i + 4].split(" ")).filter { it != "" }
            )
        )
    }

    part1(boards, bingoNumbers)
    println(part2(boards, bingoNumbers))
}
