import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val (n, k) = File("telemetry.in").bufferedReader().readLine().split(" ").map(String::toInt)
    val seq = ArrayList<String>()

    val reversed = BooleanArray(n + 1)
    val cur = CharArray(n, { '0' })
    fun go(i: Int) {
        if (i == n) {
            seq.add(cur.joinToString(""))
            return
        }

        var range: IntProgression = 0 until k
        if (reversed[i]) range = range.reversed()
        var r = reversed
        for (v in range) {
            cur[i] = '0' + v
            go(i + 1)
            reversed[i + 1] = !reversed[i + 1]
        }
    }

    go(0)

    for (i in 0 until seq.size - 1) {
        val a = seq[i]
        val b = seq[i + 1]
        var dif = 0
        for (j in 0 until n) {
            if (a[j] != b[j]) dif++
        }
        if (dif > 2) {
            throw RuntimeException()
        }
    }

    File("telemetry.out").bufferedWriter().use { it.write(seq.joinToString("\n")) }
}