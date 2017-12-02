import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val n = File("partition.in").bufferedReader().readLine().toInt()
    val seq = ArrayList<String>()

    val cur = ArrayList<Int>(n)
    fun go(i: Int, sum: Int, min: Int) {
        if (sum == n) {
            seq.add(cur.joinToString("+"))
            return
        }

        for (p in min..n - sum) {
            cur.add(p)
            go(i + 1, sum + p, p)
            cur.removeAt(cur.lastIndex)
        }
    }

    go(0, 0, 1)
    File("partition.out").bufferedWriter().use { it.write(seq.joinToString("\n")) }
}