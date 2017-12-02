import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val n = File("antigray.in").bufferedReader().readLine().toInt()
    val res = ArrayList<String>()
    val nPow3 = if (n == 1) 1 else (0 until n - 1).map { 3 }.reduce(Int::times)
    for (mask in 0 until nPow3) {
        var s = ""
        var m = mask
        while (m > 0) {
            s = (m % 3).toString() + s
            m /= 3
        }
        while (s.length < n) s = "0" + s

        res.add(s)
        repeat(2) {
            s = s.map { if (it == '2') '0' else it + 1 }.joinToString("")
            res.add(s)
        }
    }
    File("antigray.out").bufferedWriter().use { it.write(res.joinToString("\n")) }
}