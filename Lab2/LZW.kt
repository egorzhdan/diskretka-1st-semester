import java.io.File
import java.util.*

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val s = File("lzw.in").bufferedReader().readLine()

    val dict = HashMap<String, Int>()
    val alpha = 'a'..'z'
    var sz = alpha.count()
    alpha.forEach { dict[it.toString()] = it.toInt() - 'a'.toInt() }

    val res = LinkedList<Int>()
    var t = ""
    s.forEach {
        val tc = t + it
        t = if (tc in dict) tc else {
            res += dict[t]!!
            dict[tc] = sz++
            "$it"
        }
    }
    if (t.isNotEmpty()) res += dict[t]!!

    File("lzw.out").writeText(res.joinToString(" "))
}
