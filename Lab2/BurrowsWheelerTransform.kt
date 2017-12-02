import java.io.File
import java.util.*

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val s = File("bwt.in").bufferedReader().readLine()
    val t = Array<String>(s.length, { s.substring(it) + s.take(it) }).sortedArray().map(String::last).joinToString("")
    File("bwt.out").writeText(t)
}
