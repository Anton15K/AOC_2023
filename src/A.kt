
import java.util.Scanner
val sc = Scanner(System.`in`)
fun solve() {
    var s = sc.nextLine()
    val n = s.length
    var pos = s.lastIndexOfAny("abcdefghijklmnopqrstuvwxyz".toCharArray())
    pos++
    while (pos < n && s[pos] == '0') {
        pos++
    }
    println(s.substring(0,pos))
}
fun main() {
    val t = sc.nextLine().toInt()
    for (i in 1..t) {
        solve()
    }

}