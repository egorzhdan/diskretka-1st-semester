import java.io.File

/**
 * @author Egor Zhdan
 */
fun main(args: Array<String>) {
    val n = File("vectors.in").bufferedReader().readLine().toInt()
    val res = ArrayList<String>()

    fun is1(mask: Int, bit: Int): Boolean = ((mask shr bit) and 1) > 0

    for (mask in 0 until (1 shl n)) {
        val ok = (0 until n - 1).none { is1(mask, it) && is1(mask, it + 1) }
        if (!ok) continue

        var s = Integer.toBinaryString(mask)
        for (t in 0 until n - s.length) {
            s = "0" + s
        }
        res.add(s)
    }
    res.add(0, res.size.toString())

    File("vectors.out").bufferedWriter().use { it.write(res.joinToString("\n")) }
}