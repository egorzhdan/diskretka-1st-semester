import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val n = File("allvectors.in").bufferedReader().readLine().toInt()
    val sb = StringBuilder()
    for (mask in 0 until (1 shl n)) {
        val s = Integer.toBinaryString(mask)
        for (t in 0 until n - s.length) {
            sb.append('0')
        }
        sb.append(s).append('\n')
    }
    File("allvectors.out").bufferedWriter().use { it.write(sb.toString()) }
}