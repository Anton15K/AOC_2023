import kotlin.math.max
import kotlin.math.min
import java.math.BigInteger
data class Triplet(val start1: Long, val start2: Long, val step: Long)

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

        var ans = 1e18.toLong()
        val cur = input[0].removePrefix("seeds: ").split(" ").map { it.toLong() }.toMutableList()

        var tempL = mutableListOf(mutableListOf<Triplet>())
        var trpls = mutableListOf<Triplet>()

        for (line in input) {
            if (line.startsWith("seeds:")) {
                continue
            }
            if (line.isEmpty()) continue

            if (line[0].isLetter()) {
                tempL.add(trpls)
                trpls.clear()
            } else {
                val temp = line.split(" ").map { it.toLong() }
                trpls.add(Triplet(temp[0], temp[1], temp[2]))
            }
        }
        tempL.add(trpls)
        tempL.removeAt(0)
        for (i in 0 until cur.size step 2) {
            val left = cur[i]
            val right = cur[i + 1] + left
            for (i in left until right step 1000) {
                var res = i
                for (trpl in tempL) {
                    for (triplet in trpl) {
                        if (res >= triplet.start2 && res < triplet.start1 + triplet.step) {
                            res = (res - triplet.start2 + triplet.start1)
                            break
                        }
                    }
                }
                ans = min(ans, res)
            }

        }
        println(tempL)
        return ans
    }


    val input = readInput("Day05")
    //part1(input).println()
    part2(input).println()
}