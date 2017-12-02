import java.io.File
import java.util.*

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val n = File("permutations.in").bufferedReader().readLine().toInt()
    val res = ArrayList<String>()

    val stack = Stack<Int>()

    fun go(i: Int) {
        if (i == n) {
            res.add(stack.joinToString(" "))
            return
        }

        for (d in 1..n) {
            if (d !in stack) {
                stack.push(d)
                go(i + 1)
                stack.pop()
            }
        }
    }

    go(0)

    File("permutations.out").bufferedWriter().use { it.write(res.joinToString("\n")) }
}