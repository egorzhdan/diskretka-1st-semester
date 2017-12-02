import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val (n, k) = File("part2sets.in").bufferedReader().readLine().split(" ").map(String::toInt)
    val seq = ArrayList<Array<String>>()

    File("part2sets.out").bufferedWriter().use { out ->
        val cur = IntArray(n)
        var setCount = 0
        fun go(i: Int) {
            if (i == n) {
                if (setCount != k) return

                val s = Array<String>(setCount, { "" })
                for (j in 0 until n) {
                    s[cur[j]] += "${j + 1} "
                }

                if (s.none { it.isEmpty() })
                    out.write(s.joinToString("\n") + "\n\n")
                return
            }

            for (p in 0 until setCount) {
                cur[i] = p
                go(i + 1)
            }
            cur[i] = setCount++
            go(i + 1)
            setCount--
        }

        go(0)
    }
}