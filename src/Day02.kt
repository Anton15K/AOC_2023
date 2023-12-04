private fun lims(p: Pair<Int, String>): Boolean {
    return when (p.second) {
        "red" -> {
            p.first <= 12
        }
        "green" -> {
            p.first <= 13
        }
        "blue" -> {
            p.first <= 14
        }
        else -> false
    }
}
private fun String.mp() =
    this.split(",").flatMap { it.split(";") }.map { it.split(" ").filter { it1 -> it1 != "" } }.map { Pair(it[0].toInt(), it[1]) }
fun main() {

    fun part1(input: List<String>): Int {
        var res = 0
        var id = 1
        for (line in input) {
            val values = if (id < 10) {
                line.removeRange(0..6).mp()
            } else if (id < 100) {
                line.removeRange(0..7).mp()
            } else {
                line.removeRange(0..8).mp()
            }
            var ch = 1
            for (value in values) {
                if (!lims(value)) {
                    ch = 0
                    break
                }
            }
            res += ch * id
            id++
        }
        return res
    }

    fun part2(input: List<String>): Int {
        return input.size
    }


    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}