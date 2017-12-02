import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val s = File("mtf.in").bufferedReader().readLine()

    val res = IntArray(s.length)
    val alpha = ('a'..'z').toMutableList()
    s.forEachIndexed { i, c ->
        val priority = alpha.indexOf(c)
        res[i] = priority + 1
        if (priority != 0) {
            alpha.removeAt(priority)
            alpha.add(0, c)
        }
    }
    File("mtf.out").writeText(res.joinToString(" "))
}
