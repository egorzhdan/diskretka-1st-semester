import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val n = File("nextvector.in").bufferedReader().readLine()

    File("nextvector.out").bufferedWriter().use {

        val prev = n.toCharArray()
        val next = n.toCharArray()

        val isMin = n.none { it == '1' }
        if (isMin) {
            it.write("-")
        } else {
            for (i in prev.size - 1 downTo 0) {
                if (prev[i] == '0') prev[i] = '1'
                else {
                    prev[i] = '0'
                    break
                }
            }
            it.write(prev.joinToString(""))
        }
        it.newLine()

        val isMax = n.none { it == '0' }
        if (isMax) {
            it.write("-")
        } else {
            for (i in next.size - 1 downTo 0) {
                if (next[i] == '1') next[i] = '0'
                else {
                    next[i] = '1'
                    break
                }
            }
            it.write(next.joinToString(""))
        }

    }
}