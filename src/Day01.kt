fun main() {
    fun part1(input: List<String>): Int {
        var res = 0
        for (line in input) {
            var temp = 0
            for (c in line) {
                if (c.isDigit()) {
                    temp = 10 * c.digitToInt()
                    break
                }
            }
            val line2 = line.reversed()
            for (c in line2) {
                if (c.isDigit()) {
                    temp += c.digitToInt()
                    break
                }
            }
            res += temp
        }
        return res
    }
    fun part2(input: List<String>): Int {
        var res = 0
        val lets = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val mp = mutableMapOf<String, Int>()
        for (i in 0..8) {
            mp[lets[i]] = i + 1
        }
        for (line in input) {
            val firstDigit = line.findAnyOf(digits)
            val secondDigit = line.findLastAnyOf(digits)
            res += firstDigit.requireDigit() * 10 + secondDigit.requireDigit()
        }
        return res
    }
    val input = readInput("Day01")
    println("Part 1: " + part1(input))
    println("Part 2: " + part2(input))
}

private fun Pair<Int, String>?.requireDigit(): Int {
    checkNotNull(this)
    return second.digitToInt()
}
private val digits = setOf(
    "1", "2", "3", "4", "5", "6", "7", "8", "9",
    "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
)
private fun String.digitToInt(): Int = when (this) {
    "one" -> 1
    "two" -> 2
    "three" -> 3
    "four" -> 4
    "five" -> 5
    "six" -> 6
    "seven" -> 7
    "eight" -> 8
    "nine" -> 9
    else -> single().digitToInt()
}