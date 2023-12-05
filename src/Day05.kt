data class Triplet (val start1 : Long, val start2 : Long, val step: Long)

fun main() {
    fun part1(input: List<String>): Long {
        val cur = input[0].removePrefix("seeds: ").split(" ").map { it.toLong() }.toMutableList()

        val tempL = mutableListOf<Triplet>()
        for (line in input) {
            if (line.startsWith("seeds:")) {
                continue
            }
            try {
                if (line[0].isLetter()) {
                    for (i in 0 until cur.size) {
                        for (triplet in tempL) {
                            if (cur[i] >= triplet.start2 && cur[i] < triplet.start2 + triplet.step) {
                                cur[i] = cur[i] - triplet.start2 + triplet.start1
                                break
                            }
                        }
                    }
                    println(cur)
                    tempL.clear()
                } else {
                    val temp = line.split(" ").map { it.toLong() }
                    tempL.add(Triplet(temp[0], temp[1], temp[2]))
                }
            } catch (e: Exception) {
                continue
            }

        }
        for (i in 0 until cur.size) {
            for (triplet in tempL) if (cur[i] >= triplet.start2 && cur[i] < triplet.start2 + triplet.step) {
                cur[i] = cur[i] - triplet.start2 + triplet.start1
                break
            }
        }
        tempL.clear()
        return cur.min()
    }


    fun part2(input: List<String>): Long {
        return input.size.toLong()
    }


    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}