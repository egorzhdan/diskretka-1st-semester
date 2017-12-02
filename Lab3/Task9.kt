import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val n = File("brackets.in").bufferedReader().readLine().toInt()
    val seq = ArrayList<String>()

    val cur = CharArray(n * 2)
    fun go(i: Int, balance: Int, openingLeft: Int) {
        if (i == 2 * n) {
            seq.add(cur.joinToString(""))
            return
        }

        if (openingLeft > 0) {
            cur[i] = '('
            go(i + 1, balance + 1, openingLeft - 1)
        }
        if (balance > 0) {
            cur[i] = ')'
            go(i + 1, balance - 1, openingLeft)
        }
    }

    go(0, 0, n)
    File("brackets.out").bufferedWriter().use { it.write(seq.joinToString("\n")) }
}