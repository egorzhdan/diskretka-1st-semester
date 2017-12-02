import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val n = File("gray.in").bufferedReader().readLine().toInt()
    val res = ArrayList<String>()
    res.add("0")
    res.add("1")
    for (i in 1 until n) {
        res.addAll(res.reversed())
        for (j in 0 until res.size) {
            res[j] = (if (j < res.size / 2) "0" else "1") + res[j]
        }
    }
    File("gray.out").bufferedWriter().use { it.write(res.joinToString("\n")) }
}
