import java.util.Comparator
private fun Char.get_val() : Int {
    return when (this) {
        '1' -> 1
        '2' -> 2
        '3' -> 3
        '4' -> 4
        '5' -> 5
        '6' -> 6
        '7' -> 7
        '8' -> 8
        '9' -> 9
        'T' -> 10
        'J' -> 11
        'Q' -> 12
        'K' -> 13
        'A' -> 14
        else -> 0
    }
}
/*
32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483
 */

private data class Player(val value: Int, val length: Int, val bid: Int) {
    fun compare(other: Player): Int {
        if (this.length == other.length) {
            return this.value.compareTo(other.value)
        }
        return this.length.compareTo(other.length)
    }
}
fun main() {
    fun part1(input: List<String>): Int {
        var all_cards = mutableListOf<Player>()
        var all_bids = mutableListOf<Int>()
        for (line in input) {
            val cards = line.subSequence(0, 5).map{it.get_val()}.sorted()
            val bids = line.subSequence(6, line.length).toString().toInt()
            var total_value = Pair(1, cards.max())
            var seq = 1
            var prev = cards[0]
            var all_hand = mutableListOf<Pair<Int, Int>>()
            for (i in 1..4) {
                if (cards[i] == prev) {
                    seq++
                } else {
                    all_hand.add(Pair(seq, prev))
                    seq = 1
                    prev = cards[i]
                }
            }
            all_hand.add(Pair(seq, prev))
            all_hand.sortWith(Comparator { o1, o2 -> o1.first.compareTo(o2.first) })
            all_hand.reverse()
            println(all_hand)
            if (all_hand[0].first == 3) {
                if (all_hand[1].first == 2) {
                    total_value = Pair(5, all_hand[0].second)
                } else {
                    total_value = Pair(4, all_hand[0].second)
                }
            } else if (all_hand[0].first == 2) {
                if (all_hand[1].first == 2) {
                    total_value = Pair(3, all_hand[0].second)
                } else {
                    total_value = Pair(1, all_hand[0].second)
                }
            } else if (all_hand[0].first > 3) {
                total_value = Pair(all_hand[0].first+2, all_hand[0].second)
            } else {
                total_value = Pair(all_hand[0].first, all_hand[0].second)
            }
            all_cards.add(Player(total_value.second, total_value.first, bids))
        }
        all_cards.sortWith(Comparator { o1, o2 -> o1.compare(o2) })
        println(all_cards)
        var res = 0
        for (i in 1..5) {
            res += all_cards[i-1].bid * i
        }
        println(all_cards)
        return res
    }


    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:

    val input = readInput("Day07")
    part1(input).println()
    part2(input).println()
}
